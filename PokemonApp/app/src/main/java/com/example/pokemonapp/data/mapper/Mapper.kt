package com.example.pokemonapp.data.mapper

import com.example.pokemonapp.data.remote.model.Pokemon

fun com.example.pokemonapp.data.remote.response.PokemonResult.toDomainModel() : Pokemon {
    return Pokemon(
        name = name,
        imageUrl = imageUrl,
        url = url
    )
}