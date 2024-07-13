package com.example.pokemonapp.data.remote.response.PokemonDetail

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.example.pokemonapp.data.remote.response.PokemonDetail.MoveLearnMethod,
    val version_group: com.example.pokemonapp.data.remote.response.PokemonDetail.VersionGroup
)