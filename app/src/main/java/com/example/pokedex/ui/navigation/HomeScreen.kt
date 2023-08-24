package com.example.pokedex.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pokedex.ui.components.PokemonList
import com.example.pokedex.viewmodel.PokemonViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: PokemonViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        PokemonList(viewModel = viewModel, navController = navController)
    }
}