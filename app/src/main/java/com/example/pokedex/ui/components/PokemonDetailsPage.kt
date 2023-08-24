package com.example.pokedex.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.theme.getTypeColor
import com.example.pokedex.utils.UnitsConversion

@Composable
fun PokemonPage(pokemon : Pokemon){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        PokemonDetailsCard(pokemon = pokemon)
        PokemonSpecRow(key = "Height", value = "${String.format("%.2f", UnitsConversion.decimetersToInches(pokemon.height.toDouble()))} \"")
        PokemonSpecRow(key = "Weight", value = "${String.format("%.2f", UnitsConversion.hectogramsToPounds(pokemon.weight.toDouble()))} lbs")
        PokemonSpecRow(key = "Order", value = "${pokemon.order}")
    }
}

@Composable
fun SpecText(text: String){
    Text(
        style = TextStyle(fontSize = 18.sp),
        text = text
    )
}

@Composable
fun PokemonSpecRow(key : String, value: String){
    Column (modifier = Modifier.padding(30.dp)){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SpecText(text = key)
            SpecText(text = value)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = Color.Gray)
    }

}

@Composable
private fun PokemonDetailsTitle(text: String) {
    Text(
        modifier = Modifier,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = MaterialTheme.colorScheme.background,
            shadow = Shadow(
                color = Color.Black,
                blurRadius = 3f,
                offset = Offset(2f, 2f)
            )
        ),
        text = text
    )
}

@Composable
fun PokemonDetailsCard(pokemon: Pokemon){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    getTypeColor(pokemon.types[0].type.name),
                    MaterialTheme.colorScheme.background
                ),
                start = Offset(0f, 0f),
                end = Offset(0f, 700f)
            ),
        ),
        contentAlignment = Alignment.Center
    ){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 10.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                PokemonDetailsTitle(text = pokemon.name.replaceFirstChar { it.titlecase() })
                PokemonDetailsTitle("#${String.format("%04d", pokemon.id)}")
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
            ){
                PokemonImage(
                    pokemonName = pokemon.name,
                    pokemonImageUrl = pokemon.sprites.other.officialArtwork.frontDefault,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(10.dp)
                )
                Types(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterVertically),
                    types = pokemon.types
                )
            }
        }
    }

}