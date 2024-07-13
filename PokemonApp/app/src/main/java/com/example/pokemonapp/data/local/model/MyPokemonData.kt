package com.example.pokemonapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class MyPokemonData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val image : String,
    val nickname: String)
