package com.example.cheas_stoeckli.ui.enums

import androidx.annotation.DrawableRes
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.navigation.FavoriteRoute
import com.example.cheas_stoeckli.navigation.NewsRoute
import com.example.cheas_stoeckli.navigation.OfferRoute
import com.example.cheas_stoeckli.navigation.TeamRoute


enum class TabItem(
    val route: Any,
    val title: String,
   @DrawableRes val tabIcon: Int
) {
    NEWS(NewsRoute, "Grüezi ", R.drawable.waving_hand_24),
    ANGEBOT(OfferRoute, "s'Angebot", R.drawable.shopping_bag_24),
    TEAM(TeamRoute, "s'Team", R.drawable.groups_24),
    KONTAKT(TeamRoute, "Kontakt", R.drawable.near_me_24),
    FAVORITEN(FavoriteRoute, "Favoriten", R.drawable.favorite_24)
}