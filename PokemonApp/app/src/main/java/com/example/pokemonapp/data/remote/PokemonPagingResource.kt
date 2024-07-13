package com.example.pokemonapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp.data.remote.response.PokemonResult

class PokemonPagingResource(private val api: PokemonApi)
    : PagingSource<Int, com.example.pokemonapp.data.remote.response.PokemonResult>()
{
    override fun getRefreshKey(state: PagingState<Int, com.example.pokemonapp.data.remote.response.PokemonResult>): Int? {
        return state.anchorPosition?.let {
            anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.pokemonapp.data.remote.response.PokemonResult> {
        return try {
            val currentPage = params.key ?: 0
            val response = api.getListPokemon(limit = 20, offset = currentPage *20)
            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage==0) null else currentPage -1,
                nextKey = if ((currentPage +1) * 20 >= response.count) null else currentPage + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}