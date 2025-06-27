package com.example.cheas_stoeckli.data.remote.GoogleDirections

import com.example.cheas_stoeckli.domain.models.DirectionsRoute

data class DirectionsResponse(
    val routes: List<DirectionsRoute>
)