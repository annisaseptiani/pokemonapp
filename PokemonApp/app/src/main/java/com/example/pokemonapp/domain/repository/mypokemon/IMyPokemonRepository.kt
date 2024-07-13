package com.example.pokemonapp.domain.repository.mypokemon

import com.example.pokemonapp.data.local.dao.PokemonDao
import com.example.pokemonapp.data.local.model.MyPokemonData
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class IMyPokemonRepository @Inject constructor
    (private val pokemonDao: PokemonDao) : MyPokemonRepository {
    override suspend fun insert(myPokemonData: MyPokemonData) {
        pokemonDao.insert(myPokemonData)
    }

    override fun getAllData(): Flow<List<MyPokemonData>> {
        return pokemonDao.getAllData()
    }

    override suspend fun delete(id: Int) {
        pokemonDao.deleteDatabyId(id)
    }

    override suspend fun edit(name: String, id: Int) {
        pokemonDao.renameData(name, id)
    }

}