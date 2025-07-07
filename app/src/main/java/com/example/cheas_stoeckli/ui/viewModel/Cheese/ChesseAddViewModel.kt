package com.example.cheas_stoeckli.ui.viewModel.Cheese

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Cheese.CheeseAddRepository
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.ui.enums.MilkType
import kotlinx.coroutines.launch

class CheeseAddViewModel(
    private val cheeseAddRepo: CheeseAddRepository,
    private val cloudRepo: CloudStorageRepository
) : ViewModel() {


    var name = mutableStateOf("")
    var description = mutableStateOf("")
    var milkType by mutableStateOf<MilkType?>(null)

    var errorMessage by mutableStateOf("")

    val previewCheese: Cheese
        get() = Cheese(
            name = name.value,
            description = description.value,
            milkType = milkType ?: MilkType.ALL,

        )

    fun saveCheese(
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                    val cheese = Cheese(
                        name = name.value,
                        description = description.value,
                        milkType = milkType ?: MilkType.ALL,
                    )
                    cheeseAddRepo.addCheese(cheese, onSuccess)
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
        milkType = null
    }
}

