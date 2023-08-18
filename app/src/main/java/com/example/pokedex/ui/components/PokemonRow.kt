package com.example.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.theme.getTypeColor

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
                        colors = listOf(getTypeColor(pokemon.types[0].type.name), Color.White),
                        start = Offset(0f, 0f),
                        end = Offset(0f, 500f)
                    )
                )
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            PokemonImage(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                pokemonName = pokemon.name,
                pokemonImageUrl = pokemon.sprites.frontDefault
            )
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
            Types(
                modifier = Modifier.fillMaxSize(),
                types = pokemon.types
            )
        }
    }
}