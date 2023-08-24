package com.example.pokedex.ui.screens

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object PokemonDetailScreen: Screen("pokemon_detail/{id}")
}