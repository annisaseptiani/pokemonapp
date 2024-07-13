package com.example.pokemonapp.ui.bottomnavigation

import okhttp3.Route

sealed class Routes(val route: String) {
    object Home : Routes("homescreen")
    object DetailPokemon : Routes("detailpokemon")
}