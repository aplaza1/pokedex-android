package com.example.pokedex.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.pokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonList(viewModel: PokemonViewModel) {
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