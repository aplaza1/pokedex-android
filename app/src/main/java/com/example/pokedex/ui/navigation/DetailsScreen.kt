package com.example.pokedex.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.components.PokemonPage
import com.example.pokedex.viewmodel.PokemonViewModel

@Composable
fun DetailsScreen(id: Int, viewModel: PokemonViewModel) {
    val pokemonList = viewModel.pokemonList.value;
    Log.i("DetailsScreen", "pokemonList: ${pokemonList?.size}")
    val pokemon: Pokemon? = pokemonList?.find { it.id == id }
    if (pokemon != null) {
        PokemonPage(pokemon)
    }
}