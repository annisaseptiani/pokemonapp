package com.example.pokemonapp.ui.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val title: String? =null,
    val icon: ImageVector? = null
) {
    object HomeScreen : BottomNavigationItems(
        route = "homescreen",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object MyPokemonScreen : BottomNavigationItems(
        route = "mypokemon",
        title = "My Pokemon",
        icon = Icons.Outlined.FavoriteBorder
    )
}