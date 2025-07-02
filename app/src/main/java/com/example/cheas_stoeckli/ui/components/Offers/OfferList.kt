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
import com.example.cheas_stoeckli.data.Fake.offerList
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.ui.enums.OfferKind

@Composable
fun OfferList(
    offers: List<Offer>,
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
            items(offerList.filter { it.type != OfferKind.ALLGEMEIN }) { item ->
                OfferCard(
                    navigateToDetailedOffer = { },
                    offer = item,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(offerList.filter { it.type == OfferKind.ALLGEMEIN }) { item ->
                OfferCard(
                    navigateToDetailedOffer = { },
                    offer = item,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}