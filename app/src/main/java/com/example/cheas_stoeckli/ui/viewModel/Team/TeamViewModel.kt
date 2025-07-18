package com.example.cheas_stoeckli.ui.viewModel.Team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Team.TeamRepository
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TeamViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
  private val teamRepo: TeamRepository
): ViewModel() {

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val teamMembers = teamRepo.observeTeamMembers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun deleteTeamMember(teamMember: TeamMember) {
        teamRepo.deleteTeamMember(teamMember)

    }

}