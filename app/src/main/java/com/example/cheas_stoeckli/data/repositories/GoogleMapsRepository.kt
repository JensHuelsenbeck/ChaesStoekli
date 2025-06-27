package com.example.cheas_stoeckli.data.repositories

import com.cheas_stoeckli.app.BuildConfig
import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleApiService


class GoogleMapsRepository(
    private val apiService: GoogleApiService
) {

    val apiKey = BuildConfig.maps_api_key

    suspend fun GoogleMapsCall(origin: String, destination: String) {
      val polyline =  apiService.getDirections(
            origin = origin,
            destination = destination,
            apiKey = apiKey
        )
    }

    fun staticMapsCall(polyline: String): String {
       return "https://maps.googleapis.com/maps/api/staticmap" +
                "?size=600x400" +
                "&path=enc:$polyline" +
                "&key=$apiKey"
    }
}