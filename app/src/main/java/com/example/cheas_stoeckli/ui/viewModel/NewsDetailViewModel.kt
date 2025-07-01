package com.example.cheas_stoeckli.ui.viewModel

import android.Manifest
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.GoogleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NewsDetailViewModel(
   private val googleMapsRepository: GoogleRepository
) : ViewModel() {

    private val _polyline = MutableStateFlow("")
    val polyline = _polyline.asStateFlow()

    private val _staticMapUrl = MutableStateFlow("")
    val staticMapUrl = _staticMapUrl.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()


    fun getStaticMap(origin: String, destination: String) {
        viewModelScope.launch {
            try {
                val poly = googleMapsRepository.getPolyline(origin, destination)
                _polyline.value = poly
                _error.value = null
                val staticUrl = googleMapsRepository.staticMapsUrlBuilder(poly)
                _staticMapUrl.value = staticUrl
            } catch (e: HttpException) {
                Log.e(
                    "NewsDetailViewModel",
                    "Fehler beim Laden der Polyline: ${e.code()}: ${e.message()} "
                )
                _error.value = "Server-Fehler (${e.code()})"
            } catch (e: IOException) {
                Log.e("NewsDetailViewModel", "I/O Fehler beim Laden der Polyline: ${e.message}")
                _error.value = "Keine Internetverbindung"
            } catch (e: Exception) {
                Log.e(
                    "NewsDetailViewModel",
                    "Unbekannter Fehler beim Laden der Polyline: ${e.message}"
                )
                _error.value =
                    e.message ?: "Ups – da ist etwas schiefgelaufen. - Versuche es später erneut"
            }
        }
    }


    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun getMapUrl(destination: String) {
        viewModelScope.launch {
            try {
                val result = googleMapsRepository.buildMapUrl(destination)
                result.onSuccess { url ->
                    Log.d("NewsDetailViewModel", "✓ Static-Map-URL erhalten: $url")
                    _staticMapUrl.value = url
                }.onFailure { e ->
                    Log.e("NewsDetailViewModel", "✗ buildMapUrl-Fehler: ${e.message}")
                    _error.value = e.message
                }
            } catch (e: Exception) {
                Log.e("NewsDetailViewModel", "✗ Unbekannter Fehler: ${e.message}")
                _error.value = e.message
            }
        }
    }
}
