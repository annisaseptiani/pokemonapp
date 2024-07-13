package com.example.pokemonapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapp.data.local.model.MyPokemonData
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonData: MyPokemonData)

    @Query("SELECT * FROM pokemon_table")
    fun getAllData(): Flow<List<MyPokemonData>>

    @Query("DELETE FROM pokemon_table WHERE id = :id")
    suspend fun deleteDatabyId(id: Int)

    @Query("UPDATE pokemon_table SET name = :name WHERE id = :id")
    suspend fun renameData(name : String, id: Int)
}