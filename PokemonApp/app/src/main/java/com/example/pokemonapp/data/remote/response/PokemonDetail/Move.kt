package com.example.pokemonapp.data.remote.response.PokemonDetail

data class Move(
    val move: com.example.pokemonapp.data.remote.response.PokemonDetail.MoveX,
    val version_group_details: List<com.example.pokemonapp.data.remote.response.PokemonDetail.VersionGroupDetail>
)