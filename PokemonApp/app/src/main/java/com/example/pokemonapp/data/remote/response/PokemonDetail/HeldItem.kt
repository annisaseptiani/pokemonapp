package com.example.pokemonapp.data.remote.response.PokemonDetail

data class HeldItem(
    val item: com.example.pokemonapp.data.remote.response.PokemonDetail.Item,
    val version_details: List<com.example.pokemonapp.data.remote.response.PokemonDetail.VersionDetail>
)