package com.example.pokemonapp.ui.pokemonlistui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemonapp.data.remote.model.Pokemon
import com.example.pokemonapp.ui.bottomnavigation.Routes
import com.example.pokemonapp.ui.common.CardItem
import com.example.pokemonapp.ui.theme.Blue1
import com.example.pokemonapp.ui.theme.Yellow1

@Composable
fun PokemonListScreen(navController: NavController, viewModel : PokemonListViewModel){
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.background(Blue1)) {
                Text(text = "Pokemon List", style = MaterialTheme.typography.titleLarge, color = Yellow1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp), textAlign = TextAlign.Center,)
            }
            PokemonList(viewModel, navController)
        }
    }

}

@Composable
fun PokemonList(viewModel: PokemonListViewModel, navController: NavController) {
    val lazyPokemonItems : LazyPagingItems<Pokemon> = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

    val loadState = lazyPokemonItems.loadState

    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()) {
        items(lazyPokemonItems.itemCount) {
            index ->
            val pokemon = lazyPokemonItems[index]
            pokemon?.let { PokemonListItem(pokemon =it, navController = navController) }
        }
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyPokemonItems.loadState.refresh as LoadState.Error
                    item {
                        BasicText(
                            text = "Refresh error: ${e.error.localizedMessage}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                loadState.append is androidx.paging.LoadState.Error -> {
                    val e = lazyPokemonItems.loadState.append as androidx.paging.LoadState.Error
                    item {
                        BasicText(
                            text = "Append error: ${e.error.localizedMessage}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
        }
    }

}


@Composable
fun PokemonListItem(pokemon: Pokemon, navController : NavController) {
    CardItem(image = pokemon.imageUrl, name = pokemon.name, onClickReleaseButton = {}, onClickRenameButton = {}, nickname = null) {
        navController.navigate("${Routes.DetailPokemon.route}/${pokemon.name}")
    }
}
