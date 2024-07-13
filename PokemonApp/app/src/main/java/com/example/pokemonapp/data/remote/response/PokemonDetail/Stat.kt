package com.example.pokemonapp.data.remote.response.PokemonDetail

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: com.example.pokemonapp.data.remote.response.PokemonDetail.StatX
)