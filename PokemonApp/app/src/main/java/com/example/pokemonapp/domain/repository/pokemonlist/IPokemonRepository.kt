package com.example.pokemonapp.domain.repository.pokemonlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokemonapp.data.mapper.toDomainModel
import com.example.pokemonapp.data.remote.PokemonApi
import com.example.pokemonapp.data.remote.PokemonPagingResource
import com.example.pokemonapp.data.remote.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class IPokemonRepository @Inject constructor(
    private val api : PokemonApi
): PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PokemonPagingResource(api)
        }.flow.map { pagingdata->
            pagingdata.map {pokemon->
                val number = if(pokemon.url.endsWith("/")) {
                    pokemon.url.dropLast(1).takeLastWhile { it.isDigit() }
                } else {
                    pokemon.url.takeLastWhile { it.isDigit() }
                }
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                pokemon.imageUrl = url
                pokemon.toDomainModel()
                Pokemon(pokemon.name,pokemon.imageUrl, pokemon.url)
            }
        }
    }

    override suspend fun getPokemonDetail(pokemonName : String): com.example.pokemonapp.data.remote.response.PokemonDetail.PokemonDetail {
        return api.getPokemonInfo(pokemonName)
    }
}