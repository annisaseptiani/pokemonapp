package com.example.pokemonapp.data.remote.response

data class PokemonResult(
    val name: String,
    val url: String
) {
    var imageUrl :String  = ""
}