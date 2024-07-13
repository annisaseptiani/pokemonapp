package com.example.pokemonapp.domain.repository.backend

import com.example.pokemonapp.data.remote.BackendAPI
import javax.inject.Inject

class IBackendRepository @Inject constructor(private val api : BackendAPI) : BackendRepository {
    override suspend fun catchPokemon(): com.example.pokemonapp.data.remote.response.BackendResponse {
        return api.catchPokemon()
    }

    override suspend fun releasePokemon(): com.example.pokemonapp.data.remote.response.BackendResponse {
        return api.releasePokemon()
    }


    override suspend fun renamePokemon(name : String): String {
        return api.renamePokemon(name)
    }
}