package com.example.cheas_stoeckli.data.repositories

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.cheas_stoeckli.app.BuildConfig
import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleApiService
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
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
    suspend fun getFreshLocation(): Result<Location> {
        return try {
            val fused = LocationServices.getFusedLocationProviderClient(context)
            val location = fused.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).await()

            if (location != null) {
                Result.success(location)
            } else {
                Result.failure(Exception("Standort ist null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun buildMapUrl(destination: String): Result<String> {
        return getFreshLocation().mapCatching { location ->
            val origin = "${location.latitude},${location.longitude}"
            val poly = getPolyline(origin, destination)
            staticMapsUrlBuilder(poly)
        }
    }
}
