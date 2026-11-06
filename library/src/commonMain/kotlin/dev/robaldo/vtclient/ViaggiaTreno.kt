// Copyright (C) 2026 c4lopsitta (github username)
// SPDX-License-Identifier: AGPL-3.0-or-later

package dev.robaldo.vtclient

import dev.robaldo.vtclient.enums.DetailType
import dev.robaldo.vtclient.enums.Region
import dev.robaldo.vtclient.models.RestEasyTrainData
import dev.robaldo.vtclient.models.stations.Station
import dev.robaldo.vtclient.models.stations.StationSearchResult
import dev.robaldo.vtclient.models.trains.AutocompletedTrain
import dev.robaldo.vtclient.models.trains.StationBoardTrain
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.Instant


/**
 * API Interface to Trenitalia's ViaggiaTreno web service.
 *
 * @property httpClient The KTOR HTTP Client to use for requests.
 *
 * @since 0.0.1
 * @author c4lopsitta
 */
class ViaggiaTreno(
    private val httpClient: HttpClient,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    /** List stations for a given [Region]
     *
     * @param region The [Region] for which to list the stations.
     *
     * @since 0.0.1
     * @return List of [Station]s for the given [Region], `null` if the response is empty.
     *
     * @throws Exception on HTTP Status Codes that are not 200
     */
    suspend fun listStations(region: Region): List<Station>? {
        val requestUrl = "${VT_BASE_URL}/elencoStazioni/${region.id}"

        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return null;

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<List<Station>>(response.bodyAsText())
    }

    /** Searches for a station by name.
     *
     * @param stationName The name of the station to search for.
     *
     * @return List of [StationSearchResult]s matching the given [stationName], empty if none are found.
     *
     * @throws Exception on HTTP Status Codes that are not 200
     */
    suspend fun searchStation(stationName: String): List<StationSearchResult> {
        val requestUrl = "${VT_BASE_URL}/cercaStazione/${stationName}"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return emptyList()

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<List<StationSearchResult>>(
            response.bodyAsText()
        )
    }

    /** Autocompletes a station name, returning a list of [StationSearchResult]s.
     *
     * @param stationName The name of the station to autocomplete.
     *
     * @return List of [StationSearchResult]s, empty if no results are found.
     *
     * @throws Exception on HTTP Status Codes that are not 200
     */
    suspend fun autocompleteStation(stationName: String): List<StationSearchResult> {
        val requestUrl = "${VT_BASE_URL}/autocompletaStazione/${stationName}"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return emptyList()

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        val stations: MutableList<StationSearchResult> = mutableListOf()

        // TSV lines => NAME|STATION ID
        response.bodyAsText().lines().forEach { line ->
            StationSearchResult.autoCompleteConstructor(line)?.let { stations.add(it) }
        }

        return stations.toList()
    }

    /** Returns the [Region] of a given [Station].
     *
     * @param station A [Station] to look up the region of.
     *
     * @return A Region, null if the API response is empty
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun regionFromStation(station: Station): Region? {
        return this.regionFromStation(station.id)
    }

    /** Returns the [Region] of a given [Station].
     *
     * @param station A [StationSearchResult] to look up the region of.
     *
     * @return A Region, null if the API response is empty
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun regionFromStation(station: StationSearchResult): Region? {
        return this.regionFromStation(station.id)
    }

    /** Returns the [Region] of a given [Station].
     *
     * @param stationId The ID of the [Station] to look up the region of.
     *
     * @return A Region, null if the API response is empty
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun regionFromStation(stationId: String): Region? {
        val requestUrl = "${VT_BASE_URL}/regione/${stationId}"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return null

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        val regionId = response.bodyAsText().toIntOrNull() ?: return null
        return Region.entries[regionId]
    }

    suspend fun listStationsForCity(stationId: String): List<StationSearchResult> {
        val requestUrl = "${VT_BASE_URL}/elencoStazioniCitta/${stationId}"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return emptyList()
        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<List<StationSearchResult>>(response.bodyAsText())
    }

    /** Returns a new [Station] instance for a given [Station] ID, containing all the information available.
     *
     * @param station The [Station] to look up the details of.
     *
     * @return A new [Station] instance.
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun stationDetails(station: Station): Station? {
        return this.stationDetails(station.id, station.region)
    }

    /** Returns a new [Station] instance for a given [StationSearchResult], containing all the information available.
     *
     * @param station The [StationSearchResult] to look up the details of.
     *
     * @return A new [Station] instance.
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun stationDetails(station: StationSearchResult): Station? {
        return this.stationDetails(station.id)
    }

    /** Returns a new [Station] instance for a given [Station] ID, containing all the information available.
     *
     * @param station The Station ID to look up the details of.
     * @param region The [Region] of the station, if not provided it will be looked up automatically.
     *
     * @return A new [Station] instance.
     *
     * @throws Exception On HTTP Status Codes other than 200.
     */
    suspend fun stationDetails(station: String, region: Region? = null): Station? {
        val actualRegion = region ?: regionFromStation(station) ?: throw Exception("Invalid station ID or API Error. Region ID could not be retrieved")
        val requestUrl = "${VT_BASE_URL}/dettaglioStazione/${station}/${actualRegion.id}"
        val response = httpClient.get(requestUrl)
        if ( response.bodyAsText().isEmpty() ) return null

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<Station>(response.bodyAsText())
    }

    suspend fun autocompleteTrainFromNumber(runningTrainNumber: Int): List<AutocompletedTrain> {
        val requestUrl = "${VT_BASE_URL}/cercaNumeroTrenoTrenoAutocomplete/${runningTrainNumber}"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return emptyList()

        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return response.bodyAsText().lines().filter{ it.isNotEmpty() }.map { line ->
            AutocompletedTrain.autocompleteConstructor(line)!!
        }
    }

    suspend fun getTrainDetails(train: AutocompletedTrain): RestEasyTrainData? {
        return this.getTrainDetails(
            originStationId = train.originStationId,
            runningTrainNumber = train.trainNumber,
            departureTime = train.departureDate
        )
    }

    /**
     *
     * @param originStationId The [Station] ID of the train's origin.
     * @param runningTrainNumber The train's number.
     * @param departureTime A UNIX Epoch Milliseconds timestamp of the departure time.
     *
     * @throws Exception if the API returns an error.
     */
    suspend fun getTrainDetails(
        originStationId: String,
        runningTrainNumber: String,
        departureTime: ULong
    ): RestEasyTrainData? {
        val requestUrl = "$VT_BASE_URL/andamentoTreno/$originStationId/$runningTrainNumber/$departureTime"
        val response = httpClient.get(requestUrl)

        if ( response.bodyAsText().isEmpty() ) return null
        if ( response.status != HttpStatusCode.OK ) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<RestEasyTrainData>(response.bodyAsText())
    }

    /**
     * Fetch all departures or arrivals for a given station at a given time.
     *
     * @param stationId The [Station] ID of the station to get the trains for.
     * @param time A Kotlin Datetime Instant from which the Station's trains will be fetched. Defaults to `Clock.System.now()`
     * @param detailType The type of Trains to fetch. can be [DetailType.DEPARTURES] or [DetailType.ARRIVALS]
     *
     * @throws Exception if the API returns an error.
     *
     * @see [DetailType]
     */
    suspend fun getStationTrains(
        stationId: String,
        time: Instant = Clock.System.now(),
        detailType: DetailType = DetailType.DEPARTURES
    ): List<StationBoardTrain> {
        val requestUrl = "${VT_BASE_URL}/${detailType.apiUrlPath}/${stationId}/${time.getViaggiatrenoTimeString()}"
        val response = httpClient.get(requestUrl)

        if (response.bodyAsText().isEmpty()) return emptyList()
        if (response.status != HttpStatusCode.OK) {
            throw Exception(response.bodyAsText())
        }

        return json.decodeFromString<List<StationBoardTrain>>(response.bodyAsText())
    }

    /**
     * Returns, if any are available, info mobility updates about the train's status.
     */
    suspend fun getTrainInfoMobility(trainNumber: Int) {
        val requestUrl = "${NEWS_BASE_URL}/infomobility?trainNumber=$trainNumber"

        val response = httpClient.get(requestUrl)

    }

    suspend fun getTrainSmartCaring(
        trainNumber: Int,
        originStationId: String,
        date: Instant = Clock.System.now()
    ) {
        val formattedDate = date.toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
        val requestUrl = "${NEWS_BASE_URL}/smartcaring?commercialTrainNumber=$trainNumber&originCode=$originStationId&searchDate=$formattedDate"

        val response = httpClient.get(requestUrl)

        // TODO))
    }

    companion object {
        private const val BASE_URL = "http://www.viaggiatreno.it/infomobilita/resteasy"
        private const val VT_BASE_URL = "${BASE_URL}/viaggiatreno"
        private const val NEWS_BASE_URL = "${BASE_URL}/news"
    }
}