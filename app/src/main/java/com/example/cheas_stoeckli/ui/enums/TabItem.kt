package com.example.cheas_stoeckli.ui.enums

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.navigation.MoreRoute
import com.example.cheas_stoeckli.navigation.NewsRoute
import com.example.cheas_stoeckli.navigation.OfferRoute
import com.example.cheas_stoeckli.navigation.TeamRoute


enum class TabItem(
    val route: Any,
    val title: String,
    @DrawableRes val tabIcon: Int,
    val iconSize: Dp = 24.dp
) {
    NEWS(NewsRoute, "Grüezi ", R.drawable.waving_hand_24),
    ANGEBOT(OfferRoute, "s'Angebot", R.drawable.cheese_48, 48.dp),
    TEAM(TeamRoute, "s'Team", R.drawable.groups_24),
    MEHR(MoreRoute, "Mehr", R.drawable.more_horiz_24),

}