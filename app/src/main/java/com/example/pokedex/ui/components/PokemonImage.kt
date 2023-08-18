package com.example.pokedex.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun PokemonImage(pokemonName: String, modifier: Modifier, pokemonImageUrl: String){
    AsyncImage(
        modifier = modifier,
        model = pokemonImageUrl,
        contentDescription = pokemonName
    )
}