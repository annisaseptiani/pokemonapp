package com.example.pokemonapp.ui.mypokemonui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonapp.data.local.model.MyPokemonData
import com.example.pokemonapp.ui.common.CardItem
import com.example.pokemonapp.ui.common.NoDataDisplay
import com.example.pokemonapp.ui.theme.Blue1
import com.example.pokemonapp.ui.theme.Yellow1

@Composable
fun MyPokemonListScreen(navController: NavController, viewModel: MyPokemonViewModel){
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.background(Blue1)) {
                Text(text = "My Pokemon List", style = MaterialTheme.typography.titleLarge, color = Yellow1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp), textAlign = TextAlign.Center,)
            }
            MyPokemonList(viewModel = viewModel)
        }
    }
}

@Composable
fun MyPokemonList(viewModel: MyPokemonViewModel) {
    val pokemonList by viewModel.pokemonList.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    LaunchedEffect(pokemonList) {
        viewModel.listPokemon()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(pokemonList.size) { index ->
                val pokemon = pokemonList[index]
                pokemon.let { MyPokemonItem(pokemonData = it, viewModel) }
            }
        }
        if (pokemonList.isEmpty()) {
            NoDataDisplay(text = "You haven't caught any pokemon", modifier = Modifier.fillMaxSize())
        }
        if (loading!!) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }



}

@Composable
fun MyPokemonItem(pokemonData: MyPokemonData, viewModel: MyPokemonViewModel) {
    val releaseResult by viewModel.releaseResult.collectAsState()
    val renameResult by viewModel.renameResult.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(releaseResult, renameResult) {
        viewModel.releaseResult.collect { show ->
            if(show!=null) {
                Toast.makeText(context, show.message, Toast.LENGTH_SHORT).show()
                viewModel.listPokemon()
                viewModel.clearResults()
            }
        }
    }
    LaunchedEffect(renameResult) {
        viewModel.renameResult.collect{
            if (!it.isNullOrEmpty()){
                Toast.makeText(context, "Rename Success", Toast.LENGTH_SHORT).show()
                viewModel.listPokemon()
                viewModel.clearResults()
            }
        }
    }

    val fullName = pokemonData.name
    val baseName = if ('-' in fullName) {
        fullName.substringBefore('-')
    } else {
        fullName
    }
    CardItem(image = pokemonData.image, name = pokemonData.name,
        onClickReleaseButton = {
            viewModel.releasePokemon(pokemonData.id) },
        onClickRenameButton = {
            viewModel.renamePokemon(name = baseName, id = pokemonData.id) },
        nickname = pokemonData.nickname) {
    }

}