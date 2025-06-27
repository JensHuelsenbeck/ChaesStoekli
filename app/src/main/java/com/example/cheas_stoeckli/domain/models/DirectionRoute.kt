package com.example.cheas_stoeckli.domain.models

import com.squareup.moshi.Json

data class DirectionsRoute(
    @Json(name = "overview_polyline")
    val overviewPolyline: Polyline
)
data class Polyline(
    val points: String
)

