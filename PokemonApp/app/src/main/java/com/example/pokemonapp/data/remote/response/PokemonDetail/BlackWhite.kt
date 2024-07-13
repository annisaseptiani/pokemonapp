package com.example.pokemonapp.data.remote.response.PokemonDetail

data class BlackWhite(
    val animated: com.example.pokemonapp.data.remote.response.PokemonDetail.Animated,
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String
)