package com.example.pokemonapp.ui.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapp.ui.mypokemonui.MyPokemonListScreen
import com.example.pokemonapp.ui.mypokemonui.MyPokemonViewModel
import com.example.pokemonapp.ui.pokemondetailui.PokemonDetailScreen
import com.example.pokemonapp.ui.pokemondetailui.PokemonDetailViewModel
import com.example.pokemonapp.ui.pokemonlistui.PokemonListScreen
import com.example.pokemonapp.ui.pokemonlistui.PokemonListViewModel

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavHostController, pokemonViewModel : PokemonListViewModel, myPokemonViewModel: MyPokemonViewModel, detailViewModel: PokemonDetailViewModel, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = Routes.Home.route, modifier = modifier) {
        composable(Routes.Home.route) {
            onBottomBarVisibilityChanged(true)
            PokemonListScreen(viewModel = pokemonViewModel, navController = navController)
        }
        composable(BottomNavigationItems.MyPokemonScreen.route){
            onBottomBarVisibilityChanged(true)
            MyPokemonListScreen(viewModel = myPokemonViewModel, navController = navController)
        }
        composable("${Routes.DetailPokemon.route}/{name}",
            arguments = listOf( navArgument(name = "name") {
                type = NavType.StringType })
        )
        {
            val pokemonName = it.arguments?.getString("name")
            onBottomBarVisibilityChanged(false)
            if (pokemonName != null) {
                PokemonDetailScreen(pokemonName = pokemonName, viewModel = detailViewModel, navController=navController)
            }
        }

    }
}