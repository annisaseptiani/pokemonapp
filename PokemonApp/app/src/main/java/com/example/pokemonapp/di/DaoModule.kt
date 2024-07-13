package com.example.pokemonapp.di

import android.content.Context
import com.example.pokemonapp.data.local.AppDatabase
import com.example.pokemonapp.data.local.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providePokemonDao(database: AppDatabase): PokemonDao {
        return database.dataDao()
    }
}