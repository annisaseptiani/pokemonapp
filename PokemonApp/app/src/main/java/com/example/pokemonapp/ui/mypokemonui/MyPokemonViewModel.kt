package com.example.pokemonapp.ui.mypokemonui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.local.model.MyPokemonData
import com.example.pokemonapp.domain.repository.mypokemon.MyPokemonRepository
import com.example.pokemonapp.domain.usecase.ReleasePokemonUseCase
import com.example.pokemonapp.domain.usecase.RenamePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
    private val repository: MyPokemonRepository,
    private val releasePokemonUseCase: ReleasePokemonUseCase,
    private val renamePokemonUseCase: RenamePokemonUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<MyPokemonData>>(emptyList())
    val pokemonList: StateFlow<List<MyPokemonData>> get() = _pokemonList

    private val _releaseResult = MutableStateFlow<com.example.pokemonapp.data.remote.response.BackendResponse?>(null)
    val releaseResult : StateFlow<com.example.pokemonapp.data.remote.response.BackendResponse?> get() = _releaseResult
    private val _renameResult = MutableStateFlow<String?>("")
    val renameResult : StateFlow<String?> get() = _renameResult
    var isLoading = MutableStateFlow<Boolean?>(false)
    var isError = MutableStateFlow<String?>("")

    init {
        listPokemon()
    }

    fun releasePokemon(id:Int) {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000)
            try {
                val release = releasePokemonUseCase.invoke()
                if (release.success) {
                    repository.delete(id = id)
                }
                _releaseResult.value = release
            } catch (e:Exception) {
                isError.value = e.message.toString()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun listPokemon() {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000)
            isLoading.value = false
            _pokemonList.value = repository.getAllData().firstOrNull() ?: emptyList()
        }
    }

    fun renamePokemon(name:String, id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val newName : String = renamePokemonUseCase.invoke(name)
                if (newName.isNotEmpty()) {
                    repository.edit(name = newName, id = id)
                    _renameResult.value = newName
                }
            } catch (e: Exception) {
                isError.value = e.message.toString()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun clearResults() {
        _releaseResult.value = null
        _renameResult.value = null
    }
}