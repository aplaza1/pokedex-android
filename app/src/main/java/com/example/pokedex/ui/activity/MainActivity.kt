package com.example.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.model.Pokemon
import java.util.Locale

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
                .padding(10.dp)
                .fillMaxHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                PokemonImage(pokemon = pokemon)
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = pokemon.name.replaceFirstChar { it.titlecase() },
                    )
            }
        }
    }

    @Composable
    fun PokemonImage(pokemon: Pokemon){
        AsyncImage(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .padding(2.dp),
            model = pokemon.sprites.frontDefault,
            contentDescription = pokemon.name
        )
    }
}