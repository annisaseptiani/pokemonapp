package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.data.local.model.MyPokemonData
import com.example.pokemonapp.domain.repository.backend.BackendRepository
import com.example.pokemonapp.domain.repository.mypokemon.MyPokemonRepository
import com.example.pokemonapp.domain.repository.pokemonlist.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(id : String): com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail {
        return repository.getPokemonDetail(id)
    }
}

class CatchPokemonUseCase @Inject constructor(private val repository: BackendRepository) {
    suspend fun invoke() : com.example.pokemonapp.data.remote.response.BackendResponse {
        return repository.catchPokemon()
    }
}

class ReleasePokemonUseCase @Inject constructor(private val repository: BackendRepository) {
    suspend fun invoke() : com.example.pokemonapp.data.remote.response.BackendResponse {
        return repository.releasePokemon()
    }
}

class RenamePokemonUseCase @Inject constructor(private val repository: BackendRepository) {
    suspend fun invoke(name:String) : String {
        return repository.renamePokemon(name)
    }
}

class SavePokemonUseCase @Inject constructor(private val repository: MyPokemonRepository) {
    suspend fun invoke(myPokemonData: MyPokemonData) {
        return repository.insert(myPokemonData)
    }
}
