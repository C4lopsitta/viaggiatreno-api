package dev.robaldo.vtclient.enums

enum class DetailType(
    val apiUrlPath: String
) {
    DEPARTURES("partenze"),
    ARRIVALS("arrivi")
}