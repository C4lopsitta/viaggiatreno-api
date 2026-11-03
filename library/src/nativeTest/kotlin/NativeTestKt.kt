import dev.robaldo.viaggiatreno.ViaggiaTreno
import dev.robaldo.viaggiatreno.enums.Region
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime

class NativeTestKt {
    private lateinit var client: HttpClient
    private lateinit var vtc: ViaggiaTreno

    @BeforeTest
    fun setup() {
        client = HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
            }
        }
        vtc = ViaggiaTreno(client)
    }

    @AfterTest
    fun teardown() {
        client.close()
    }

    @Test
    fun testListRegion() = runTest {
        val stations = assertNotNull(vtc.listStations(Region.PIEMONTE))

        assertTrue(stations.isNotEmpty(), "Expected stations in Piemonte")
        stations.forEach { println(it) }
    }

    @Test
    fun testSearchStation() = runTest {
        val stations = vtc.searchStation("milano")

        assertTrue(stations.isNotEmpty(), "Expected station search results for Milano")
        stations.forEach { println(it) }
    }

    @Test
    fun testAutocompleteStation() = runTest {
        val stations = vtc.autocompleteStation("milano")

        assertTrue(stations.isNotEmpty(), "Expected station autocomplete results for Milano")
        stations.forEach { println(it) }
    }

    @Test
    fun testStationDetails() = runTest {
        val station = assertNotNull(vtc.stationDetails("S01700"))

        assertEquals("S01700", station.id)
        println(station)
    }

    @Test
    fun autocompleteTrainAndDetails() = runTest {
        for (trainNumber in listOf(14, 659, 26358)) {
            val trains = vtc.autocompleteTrainFromNumber(trainNumber)

            assertTrue(trains.isNotEmpty(), "Expected autocomplete results for train $trainNumber")
            val details = assertNotNull(vtc.getTrainDetails(trains.first()))
            assertEquals(trains.first().trainNumber.toLong(), details.trainNumber)
            println(details)
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun stationAutocompleteLeadsToDeparturesAndOriginDetails() = runTest {
        val stations = vtc.autocompleteStation("Torino Porta Nuova")
        val station = assertNotNull(stations.firstOrNull(), "Expected Torino Porta Nuova")
        val departures = vtc.getStationTrains(station.id)

        assertTrue(departures.isNotEmpty(), "Expected departures from ${station.id}")
        departures.forEach { train ->
            val origin = train.codOrigine?.let { originId ->
                assertNotNull(vtc.stationDetails(originId), "Expected details for origin $originId")
            }

            println("${train.numeroTreno} (${train.categoria}): ${origin?.cityName} - ${train.destinazione}")
            println(train)
        }
    }
}