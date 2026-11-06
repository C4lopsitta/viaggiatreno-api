// Copyright (C) 2026 c4lopsitta (github username)
// SPDX-License-Identifier: AGPL-3.0-or-later

package dev.robaldo.vtclient.models.stations

import dev.robaldo.vtclient.enums.Region
import dev.robaldo.vtclient.serializers.RegionSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationZoomDetail(
    @SerialName("key")
    val key: String,
    @SerialName("codiceStazione")
    val stationCode: String,
    @SerialName("zoomStartRange")
    val zoomStartRange: Int,
    @SerialName("zoomStopRange")
    val zoomStopRange: Int,
    @SerialName("pinpointVisible")
    val pinpointVisible: Boolean,
    @SerialName("labelVisible")
    val labelVisible: Boolean,
    @SerialName("codiceRegione")
    @Serializable(with = RegionSerializer::class)
    val region: Region
)
