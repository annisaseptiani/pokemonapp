package com.example.pokemonapp.di

import com.example.pokemonapp.data.local.dao.PokemonDao
import com.example.pokemonapp.data.remote.BackendAPI
import com.example.pokemonapp.data.remote.PokemonApi
import com.example.pokemonapp.domain.repository.backend.BackendRepository
import com.example.pokemonapp.domain.repository.backend.IBackendRepository
import com.example.pokemonapp.domain.repository.mypokemon.IMyPokemonRepository
import com.example.pokemonapp.domain.repository.mypokemon.MyPokemonRepository
import com.example.pokemonapp.domain.repository.pokemonlist.IPokemonRepository
import com.example.pokemonapp.domain.repository.pokemonlist.PokemonRepository
import com.example.pokemonapp.domain.usecase.CatchPokemonUseCase
import com.example.pokemonapp.domain.usecase.GetPokemonListUseCase
import com.example.pokemonapp.domain.usecase.PokemonDetailUseCase
import com.example.pokemonapp.domain.usecase.ReleasePokemonUseCase
import com.example.pokemonapp.domain.usecase.RenamePokemonUseCase
import com.example.pokemonapp.domain.usecase.SavePokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi) : PokemonRepository {
        return IPokemonRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(repository: PokemonRepository): GetPokemonListUseCase {
        return GetPokemonListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(repository: PokemonRepository): PokemonDetailUseCase {
        return PokemonDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideBackEndRepository(backendAPI: BackendAPI) : BackendRepository {
        return IBackendRepository(backendAPI)
    }

    @Provides
    @Singleton
    fun provideCatchPokemonUseCase(repository: BackendRepository): CatchPokemonUseCase {
        return CatchPokemonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideReleasePokemonUseCase(repository: BackendRepository): ReleasePokemonUseCase {
        return ReleasePokemonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRenamePokemonUseCase(repository: BackendRepository): RenamePokemonUseCase {
        return RenamePokemonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMyPokemonRepository(dao: PokemonDao) : MyPokemonRepository {
        return IMyPokemonRepository(dao)
    }

    @Provides
    @Singleton
    fun provideSavePokemonUseCase(repository: MyPokemonRepository) : SavePokemonUseCase {
        return SavePokemonUseCase(repository)
    }


}