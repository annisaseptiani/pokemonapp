package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.remote.response.BackendResponse
import com.example.pokemonapp.data.remote.response.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Objects

interface BackendAPI {
    @GET("release")
    suspend fun releasePokemon() : com.example.pokemonapp.data.remote.response.BackendResponse

    @GET("catch")
    suspend fun catchPokemon() : com.example.pokemonapp.data.remote.response.BackendResponse

    @POST("rename")
    suspend fun renamePokemon(@Query("name") name: String) : String
}