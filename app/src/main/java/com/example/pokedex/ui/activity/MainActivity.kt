package com.example.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.viewmodel.PokemonViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.TypeItem
import com.example.pokedex.ui.theme.getTypeColor

class MainActivity : ComponentActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(PokemonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PokemonList()
                }
            }
        }
    }

    @Composable
    fun PokemonList() {
        val pokemonList = viewModel.pokemonList.observeAsState().value
        LazyColumn {
            items(pokemonList ?: emptyList()) { pokemon ->
                PokemonRow(pokemon = pokemon)
            }

            item {
                if (pokemonList != null) {
                    if (pokemonList.isNotEmpty()) {
                        CircularProgressIndicator()
                        LaunchedEffect(key1 = Unit) {
                            viewModel.getPokemonList()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PokemonRow(pokemon: Pokemon) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(7.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.White, getTypeColor(pokemon.types[0].type.name)),
                            start = Offset(0f, 0f),
                            end = Offset(0f, 1000f)
                        )
                    )
                    .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                PokemonImage(pokemon = pokemon)
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .widthIn(max = 170.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    ),
                    text = "#${String.format("%04d", pokemon.id)}   ${pokemon.name.replaceFirstChar { it.titlecase() }}",
                )
                Types(pokemon.types)
            }
        }
    }

    @Composable
    fun Types(types: List<TypeItem>){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.End

        ) {
            for(type in types){
                Type(type = type.type.name)
            }
        }
    }

    @Composable
    fun Type(type: String){
        Card (
            modifier = Modifier
                .width(65.dp)
                .padding(bottom = 5.dp),
            colors = CardDefaults.cardColors(
                containerColor = getTypeColor(type),
            )
        ){
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            blurRadius = 5f,
                            offset = Offset(2f, 2f)
                        )
                    ),
                    text = type.uppercase()
                )
            }
        }
    }


    @Composable
    fun PokemonImage(pokemon: Pokemon){
            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                model = pokemon.sprites.frontDefault,
                contentDescription = pokemon.name
            )
        }
}