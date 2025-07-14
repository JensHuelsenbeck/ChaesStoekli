package com.example.cheas_stoeckli.ui.components.Team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.enums.MemberPosition
import com.example.cheas_stoeckli.ui.viewModel.Team.TeamViewModel

@Composable
fun TeamList(
    user: User?,
    team: List<TeamMember>,
    viewModel: TeamViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp),
        ) {
            items(team.filter { it.position != MemberPosition.AN }) { member ->
           TeamCard(
               user = user,
               member = member,
               uri = null,
               onClickDelete = { viewModel.deleteOffer(member) },
           )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(team.filter { it.position == MemberPosition.AN }) { member ->
                TeamCard(
                    user = user,
                    member = member,
                    uri = null,
                    onClickDelete = { viewModel.deleteOffer(member) },
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}