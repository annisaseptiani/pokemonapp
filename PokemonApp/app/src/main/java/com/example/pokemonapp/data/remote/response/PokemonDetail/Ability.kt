package com.example.pokemonapp.data.remote.response.PokemonDetail

data class Ability(
    val ability: com.example.pokemonapp.data.remote.response.PokemonDetail.AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)