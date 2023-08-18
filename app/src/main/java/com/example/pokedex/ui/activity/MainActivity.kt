package com.example.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.viewmodel.PokemonViewModel
import com.example.pokedex.ui.components.PokemonList

class MainActivity : ComponentActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(PokemonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PokemonList(viewModel = viewModel)
                }
            }
        }
    }











}