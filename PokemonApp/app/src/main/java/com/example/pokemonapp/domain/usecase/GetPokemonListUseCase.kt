package com.example.pokemonapp.domain.usecase

import androidx.paging.PagingData
import com.example.pokemonapp.data.remote.model.Pokemon
import com.example.pokemonapp.domain.repository.pokemonlist.PokemonRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke() : Flow<PagingData<Pokemon>> {
        return repository.getPokemonList()
    }

}