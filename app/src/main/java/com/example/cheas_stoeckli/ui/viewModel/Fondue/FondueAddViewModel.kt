package com.example.cheas_stoeckli.ui.viewModel.Fondue

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Fondue.FondueAddRepository
import com.example.cheas_stoeckli.domain.models.Fondue
import kotlinx.coroutines.launch

class FondueAddViewModel(
    private val fondueAddRepo: FondueAddRepository,
) : ViewModel() {


    var name = mutableStateOf("")
    var description = mutableStateOf("")

    var errorMessage by mutableStateOf("")

    val previewFondue: Fondue
        get() = Fondue(
            name = name.value,
            description = description.value,
        )

    fun saveFondue(
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val fondue = Fondue(
                    name = name.value,
                    description = description.value,
                )
                fondueAddRepo.addFondue(fondue, onSuccess)
            } catch (e: Exception) {
                errorMessage = "Fehler beim  Speichern. Versuche es später erneut."
                Log.e("CheeseSave", e.message ?: "")
            } finally {
                setValuablesToEmpty()
            }
        }
    }

    fun setValuablesToEmpty() {
        name.value = ""
        description.value = ""
    }
}

