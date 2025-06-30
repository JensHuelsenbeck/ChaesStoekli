package com.example.cheas_stoeckli.data.repositories

import com.cheas_stoeckli.app.BuildConfig
import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleApiService


class GoogleMapsRepository(
    private val apiService: GoogleApiService
) {

    val apiKey = BuildConfig.maps_api_key

    suspend fun getPolyline(origin: String, destination: String): String {
        val polyline = apiService.getDirections(
            origin = origin, destination = destination, apiKey = apiKey
        )
        if (!polyline.isSuccessful) {
            throw retrofit2.HttpException(polyline)
        }

        return polyline.body()?.routes?.firstOrNull()?.overviewPolyline?.points
            ?: throw IllegalStateException("Polyline fehlt in der API-Antwort")

    }

    fun staticMapsUrlBuilder(polyline: String): String {
        return "https://maps.googleapis.com/maps/api/staticmap" +
                "?size=600x400" +
                "&path=enc:$polyline" +
                "&key=$apiKey"
    }
}