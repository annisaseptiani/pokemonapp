package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail
import com.example.pokemonapp.data.remote.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getListPokemon(@Query("limit") limit : Int,
                       @Query("offset") offset : Int) : com.example.pokemonapp.data.remote.response.PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name : String) : com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail
}