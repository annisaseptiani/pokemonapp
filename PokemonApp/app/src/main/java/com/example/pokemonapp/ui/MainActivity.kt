package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.ui.bottomnavigation.BottomBar
import com.example.pokemonapp.ui.bottomnavigation.NavigationGraph
import com.example.pokemonapp.ui.mypokemonui.MyPokemonViewModel
import com.example.pokemonapp.ui.pokemondetailui.PokemonDetailViewModel
import com.example.pokemonapp.ui.pokemonlistui.PokemonListViewModel
import com.example.pokemonapp.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val pokemonListViewModel : PokemonListViewModel by viewModels()
    private val detailViewModel : PokemonDetailViewModel by viewModels()
    private val myPokemonViewModel : MyPokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                val controller : NavHostController = rememberNavController()
                var buttonsVisible by remember {
                    mutableStateOf(true)
                }
                
                Scaffold(bottomBar = {
                    if (buttonsVisible) {
                        BottomBar(navController = controller, state = buttonsVisible, modifier = Modifier)
                    }
                }) {paddingValues ->
                        NavigationGraph(modifier = Modifier.padding(paddingValues), navController = controller, pokemonListViewModel,
                            myPokemonViewModel, detailViewModel) {
                            isVisible -> buttonsVisible = isVisible

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonAppTheme {
    }
}