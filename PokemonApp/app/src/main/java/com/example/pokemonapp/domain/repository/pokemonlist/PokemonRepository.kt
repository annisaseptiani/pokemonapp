package com.example.pokemonapp.domain.repository.pokemonlist

import androidx.paging.PagingData
import com.example.pokemonapp.data.remote.model.Pokemon
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(pokemonName : String) : com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail
}