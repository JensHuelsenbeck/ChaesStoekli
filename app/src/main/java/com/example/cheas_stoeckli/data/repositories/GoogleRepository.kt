package com.example.cheas_stoeckli.data.repositories

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.cheas_stoeckli.app.BuildConfig
import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleApiService
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await


class GoogleRepository(
    private val apiService: GoogleApiService,
    private val context: Context
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

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getCurrentLocation(): Result<Location> {
        return try {
            val fused = LocationServices.getFusedLocationProviderClient(context)
            val loc = fused.lastLocation.await()
                ?: return Result.failure(IllegalStateException("Location null"))
            Result.success(loc)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}