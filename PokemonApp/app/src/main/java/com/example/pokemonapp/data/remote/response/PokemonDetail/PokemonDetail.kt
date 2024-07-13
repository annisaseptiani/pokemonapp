package com.example.pokemonapp.data.remote.response.PokemonDetail

data class PokemonDetail(
    val abilities: List<com.example.pokemonapp.data.remote.response.PokemonDetail.Ability>,
    val base_experience: Int,
    val cries: com.example.pokemonapp.data.remote.response.PokemonDetail.Cries,
    val forms: List<com.example.pokemonapp.data.remote.response.PokemonDetail.Form>,
    val game_indices: List<com.example.pokemonapp.data.remote.response.PokemonDetail.GameIndice>,
    val height: Int,
    val held_items: List<com.example.pokemonapp.data.remote.response.PokemonDetail.HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.example.pokemonapp.data.remote.response.PokemonDetail.Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: com.example.pokemonapp.data.remote.response.PokemonDetail.Species,
    val sprites: com.example.pokemonapp.data.remote.response.PokemonDetail.Sprites,
    val stats: List<com.example.pokemonapp.data.remote.response.PokemonDetail.Stat>,
    val types: List<com.example.pokemonapp.data.remote.response.PokemonDetail.Type>,
    val weight: Int
)