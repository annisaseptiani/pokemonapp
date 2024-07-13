package com.example.pokemonapp.ui.pokemondetailui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.local.model.MyPokemonData
import com.example.pokemonapp.domain.usecase.CatchPokemonUseCase
import com.example.pokemonapp.domain.usecase.PokemonDetailUseCase
import com.example.pokemonapp.domain.usecase.SavePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(val pokemonDetailUseCase: PokemonDetailUseCase,
                                                 val catchPokemonUseCase: CatchPokemonUseCase, val savePokemonUseCase: SavePokemonUseCase
)
    : ViewModel() {

        private val _pokemonDetail = MutableStateFlow<com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail?>(null)
    val pokemonDetail: StateFlow<com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail?> get() = _pokemonDetail

    private val _catchResult = MutableStateFlow<com.example.pokemonapp.data.remote.response.BackendResponse?>(null)
    val catchResult : StateFlow<com.example.pokemonapp.data.remote.response.BackendResponse?> get() = _catchResult
    var isLoading = MutableStateFlow<Boolean?>(false)
    val loadingState : StateFlow<Boolean?> get() = isLoading
    var isError = MutableStateFlow<String?>("")
    val errorState : StateFlow<String?> get() = isError

    fun fetchPokemonDetail(pokemonName : String) {
        viewModelScope.launch {
            val detail = pokemonDetailUseCase(pokemonName)
            _pokemonDetail.value=detail
        }
    }

    fun catchPokemon() {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000)
            try {
                val catch = catchPokemonUseCase.invoke()
                _catchResult.value = catch
            } catch (e:Exception) {
                isError.value = e.message.toString()
            } finally {
                isLoading.value = false
            }

        }
    }

    fun saveCatchedPokemon(pokemon : MyPokemonData) {
        viewModelScope.launch {
            savePokemonUseCase.invoke(pokemon)
        }
    }

    fun resetCatchState () {
        _catchResult.value = null
    }
}