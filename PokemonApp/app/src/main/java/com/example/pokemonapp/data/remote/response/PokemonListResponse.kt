package com.example.pokemonapp.data.remote.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<com.example.pokemonapp.data.remote.response.PokemonResult>
)