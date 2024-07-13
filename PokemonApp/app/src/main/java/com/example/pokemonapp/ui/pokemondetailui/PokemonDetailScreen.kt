package com.example.pokemonapp.ui.pokemondetailui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonapp.data.local.model.MyPokemonData
import com.example.pokemonapp.ui.common.ResponseDialog
import com.example.pokemonapp.ui.common.SmallButton
import com.example.pokemonapp.ui.theme.Bg4
import com.example.pokemonapp.ui.theme.Blue1
import com.example.pokemonapp.ui.theme.Yellow1

@Composable
fun PokemonDetailScreen(pokemonName : String, viewModel: PokemonDetailViewModel, navController: NavController) {
    val context = LocalContext.current
    val pokemonDetail by viewModel.pokemonDetail.collectAsState()
    val catch by viewModel.catchResult.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val error by viewModel.errorState.collectAsState()


    LaunchedEffect(pokemonName) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

    pokemonDetail?.let {
        pokemon ->
        Column(Modifier.fillMaxSize()) {
            Column (
                Modifier
                    .background(Blue1)
                    .fillMaxWidth()){
                Icon(tint = Yellow1, imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navController.popBackStack() })
                Box(modifier = Modifier
                    .fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AsyncImage(modifier = Modifier
                        .width(300.dp)
                        .height(200.dp)
                        .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds,
                        model = pokemon.sprites.front_default, contentDescription = "Image")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pokemon.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "#${pokemon.id.toString()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Chip(text = pokemon.types.get(0).type.name)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(
                        text = "About",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Chip(text = "Weight: ${pokemon.weight}")
                    Chip(text = "Height: ${pokemon.height}")
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Moves",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Chip(text = pokemon.moves.get(0).move.name)


                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                SmallButton(color = Blue1, text = "Catch Pokemon") {
                    viewModel.catchPokemon()
                }
            }
            if (loadingState!!) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            if (error!!.isNotEmpty()) {
                Toast.makeText(context, error,Toast.LENGTH_SHORT).show()
            }


            catch?.let {
                if (it.success) SaveDialog(image = pokemon.sprites.front_default, pokemonName,viewModel) else RetryDialog(it.message, viewModel)
            }
        }
    }
}

@Composable
fun SaveDialog(image : String, name: String, viewModel: PokemonDetailViewModel) {
    var showDialog by remember { mutableStateOf(true) }
    var nickname by remember {
        mutableStateOf("")
    }
    if (showDialog) {
        ResponseDialog(
            onDismiss = { showDialog = false
                viewModel.resetCatchState()},
            dismissTitle = "Cancel",
            onQueryChange = { nickname=it },
            confirmTitle = "Save",
            title = "Success",
            image = image,
            imageVector = null,
            nickname = nickname,
        ) {
            showDialog =false
            val pokemon = MyPokemonData(image= image, name = name, nickname = nickname)
            viewModel.saveCatchedPokemon(pokemon)
            viewModel.resetCatchState()
        }
    }
}

@Composable
fun RetryDialog(message: String, viewModel: PokemonDetailViewModel) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        ResponseDialog(
            onDismiss = { showDialog=false
                viewModel.resetCatchState()},
            onQueryChange = {},
            dismissTitle = "Back",
            confirmTitle = "Retry",
            title = message,
            image = null,
            imageVector = Icons.Default.Close,
            nickname = null,
            onConfirmationRequest = {
                showDialog=false
                viewModel.catchPokemon()
                viewModel.resetCatchState()},
        )
    }
}

@Composable
fun Chip(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Bg4)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}