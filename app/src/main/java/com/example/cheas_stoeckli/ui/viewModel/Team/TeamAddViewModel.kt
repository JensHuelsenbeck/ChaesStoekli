package com.example.cheas_stoeckli.ui.viewModel.Team

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.Team.TeamAddRepository
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.ui.enums.MemberPosition
import kotlinx.coroutines.launch

class TeamAddViewModel(
    private val teamAddRepo: TeamAddRepository,
    private val cloudRepo: CloudStorageRepository
) : ViewModel() {

    var name = mutableStateOf("")
    var description = mutableStateOf("")
    var position by mutableStateOf<MemberPosition?>(null)
    var imageDownloadUrl = mutableStateOf("")
    var imagePathInsideCloud = mutableStateOf("")
    var imageUri = mutableStateOf<Uri?>(null)
    var isUploading = mutableStateOf(false)

    var errorMessage by mutableStateOf("")

    val previewTeamMember: TeamMember
        get() = TeamMember(
            name = name.value,
            description = description.value,
            imgDownloadPath = imageDownloadUrl.value,
            imgPath = imagePathInsideCloud.value,
            position = position ?: MemberPosition.NONE
        )

    fun uploadImageAndSaveTeam(
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                if(imageUri.value != null) {
                    val (downloadUrl, imagePath) = cloudRepo.imageToCloudStorage(imageUri.value!!)
                    val member = TeamMember(
                       name = name.value,
                        description = description.value,
                        position = position ?: MemberPosition.NONE,
                        imgDownloadPath = downloadUrl,
                        imgPath = imagePath
                    )
                    teamAddRepo.addTeamMember(member, onSuccess)
                } else {
                    val member = TeamMember(
                        name = name.value,
                        description = description.value,
                        position = position ?: MemberPosition.NONE,
                        imgDownloadPath = "",
                        imgPath = ""
                    )
                    teamAddRepo.addTeamMember(member, onSuccess)
                }
            } catch (e: Exception) {
                errorMessage = "Fehler beim Hochladen oder Speichern"
                Log.e("NewsSave", e.message ?: "")
            } finally {
                isUploading.value = false
                setValuablesToEmpty()
            }
        }
    }

    fun setValuablesToEmpty() {

        name.value = ""
        description.value = ""
        position = null
        imageDownloadUrl.value = ""
        imagePathInsideCloud.value = ""
        imageUri.value = null
    }
}