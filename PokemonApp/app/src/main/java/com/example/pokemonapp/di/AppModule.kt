package com.example.pokemonapp.di

import android.app.Application
import android.content.Context
import com.example.pokemonapp.data.remote.BackendAPI
import com.example.pokemonapp.data.remote.PokemonApi
import com.example.pokemonapp.util.Common.BASE_URL_BACKEND
import com.example.pokemonapp.util.Common.BASE_URL_POKEMON
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application) :Context {
        return application
    }

    @Singleton
    @Provides
    fun getPokemonClient() : PokemonApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_POKEMON)
            .client(httpClient)
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun getBackendClient() : BackendAPI {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_BACKEND)
            .client(httpClient)
            .build()
            .create(BackendAPI::class.java)
    }
}