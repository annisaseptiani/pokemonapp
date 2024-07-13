package com.example.pokemonapp.domain.repository.mypokemon

import com.example.pokemonapp.data.local.model.MyPokemonData
import kotlinx.coroutines.flow.Flow

interface MyPokemonRepository {
    suspend fun insert(myPokemonData: MyPokemonData)
    fun getAllData() : Flow<List<MyPokemonData>>
    suspend fun delete(id : Int)
    suspend fun edit(name: String, id:Int)
}