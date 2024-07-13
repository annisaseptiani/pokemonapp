package com.example.pokemonapp.ui.bottomnavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokemonapp.ui.theme.Blue1
import com.example.pokemonapp.ui.theme.Yellow1
import com.example.pokemonapp.ui.theme.Yellow2

@Composable
fun BottomBar(navController: NavController, state: Boolean,
              modifier: Modifier = Modifier)
{
    val screens = listOf(BottomNavigationItems.HomeScreen,
        BottomNavigationItems.MyPokemonScreen)
    NavigationBar(
        modifier = modifier,
        containerColor = Blue1
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title!!)},
                icon = { Icon(imageVector = screen.icon!!, contentDescription = "Icon")},
                selected = currentRoute == screen.route,
                onClick = {
                          navController.navigate(screen.route) {
                              popUpTo(navController.graph.findStartDestination().id) {
                                  saveState = true
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.White,
                    selectedTextColor = Yellow1,
                    selectedIconColor = Yellow1,
                    unselectedIconColor = Color.White,
                ),
            )
        }
    }
}