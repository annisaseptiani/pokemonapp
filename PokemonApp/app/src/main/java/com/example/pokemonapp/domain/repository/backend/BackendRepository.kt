package com.example.pokemonapp.domain.repository.backend

import com.example.pokemonapp.data.remote.response.BackendResponse

interface BackendRepository {
    suspend fun catchPokemon() : com.example.pokemonapp.data.remote.response.BackendResponse
    suspend fun releasePokemon() : com.example.pokemonapp.data.remote.response.BackendResponse
    suspend fun renamePokemon(name : String) : String
}