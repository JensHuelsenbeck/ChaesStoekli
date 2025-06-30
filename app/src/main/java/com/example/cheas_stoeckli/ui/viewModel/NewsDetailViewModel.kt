package com.example.cheas_stoeckli.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleApiService
import com.example.cheas_stoeckli.data.repositories.GoogleMapsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NewsDetailViewModel(
    apiService: GoogleApiService,
   private val googleMapsRepository: GoogleMapsRepository
) : ViewModel() {

    private val _polyline = MutableStateFlow("")
    val polyline = _polyline.asStateFlow()

    private val _staticMapUrl = MutableStateFlow("")
    val staticMapUrl = _staticMapUrl.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getStaticMap(origin: String, destination: String) {
        viewModelScope.launch {
            try {
                val poly = googleMapsRepository.getPolyline(origin, destination)
                _polyline.value = poly
                _error.value = null
                _staticMapUrl.value = googleMapsRepository.staticMapsUrlBuilder(poly)
            } catch (e: HttpException) {
                Log.e("NewsDetailViewModel", "Fehler beim Laden der Polyline: ${e.code()}: ${e.message()} ")
                _error.value = "Server-Fehler (${e.code()})"
            } catch (e: IOException) {
                Log.e("NewsDetailViewModel", "I/O Fehler beim Laden der Polyline: ${e.message}")
                _error.value = "Keine Internetverbindung"
            } catch (e: Exception) {
                Log.e("NewsDetailViewModel", "Unbekannter Fehler beim Laden der Polyline: ${e.message}")
                _error.value = e.message ?: "Ups – da ist etwas schiefgelaufen. - Versuche es später erneut"
            }
        }
    }
}