package com.example.cheas_stoeckli.domain.models

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId val id: String? = "",
    val email: String = "",
    val fullName: String = "",
    val permissionLevel: String = "0",
    val favoriteCheeseIds: List<FavoriteCheese> = emptyList(),
    val favoriteFondueIds: List<FavoriteFondue> = emptyList(),
    val favoriteRacletteIds: List<FavoriteRaclette> = emptyList(),
)