package com.example.pokemonapp.ui.pokemonlistui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemonapp.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonUseCase : GetPokemonListUseCase)
    : ViewModel() {
        val pokemonPagingFlow = pokemonUseCase().cachedIn(viewModelScope)
}