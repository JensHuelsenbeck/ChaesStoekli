package com.example.cheas_stoeckli.ui.components.Offers

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
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.enums.OfferKind
import com.example.cheas_stoeckli.ui.viewModel.OfferViewModel

@Composable
fun OfferList(
    user: User?,
    offers: List<Offer>,
    onClickToDetailedOfferScreen: (OfferKind) -> Unit,
    viewModel: OfferViewModel
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
            items(offers.filter { it.type != OfferKind.ALLGEMEIN }) { item ->
                OfferCard(
                    onClickToDetailedOfferScreen = onClickToDetailedOfferScreen,
                    offer = item,
                    onclickDelete = {  },
                    uri = null,
                    user = user,

                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(offers.filter { it.type == OfferKind.ALLGEMEIN }) { item ->
                OfferCard(
                    onClickToDetailedOfferScreen = { },
                    offer = item,
                    onclickDelete = { viewModel.deleteOffer(item) },
                    uri = null,
                    user = user,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}