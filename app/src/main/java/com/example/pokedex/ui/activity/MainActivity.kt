package com.example.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.TypeItem
import com.example.pokedex.ui.theme.getTypeColor
import com.example.pokedex.utils.UnitsConversion

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

    @Composable
    fun Types(types: List<TypeItem>, modifier: Modifier = Modifier){
        Column(
            modifier = modifier,
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
                modifier = Modifier.fillMaxWidth(),
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
    fun PokemonImage(pokemonName: String, modifier: Modifier, pokemonImageUrl: String){
        AsyncImage(
            modifier = modifier,
            model = pokemonImageUrl,
            contentDescription = pokemonName
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
    fun SpecText(text: String){
        Text(
            style = TextStyle(
                fontSize = 18.sp,

            ),
            text = text
        )
    }

    @Preview
    @Composable
    fun PokemonPage(pokemon : Pokemon = Pokemon(
        id = 1,
        name = "bulbasaur",
        types = listOf(
            TypeItem(
                slot = 1,
                type = com.example.pokedex.model.Type(
                    name = "grass",
                )
            ),
            TypeItem(
                slot = 2,
                type = com.example.pokedex.model.Type(
                    name = "poison",
                )
            )
        ),
        height = 2,
        weight = 100,
        order = 2,
        sprites = com.example.pokedex.model.Sprites(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            other = com.example.pokedex.model.Other(
                officialArtwork = com.example.pokedex.model.OfficialArtwork(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        )
    )){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            PokemonDetailsCard(pokemon = pokemon)
            PokemonSpecRow(key = "Height", value = "${String.format("%.2f", UnitsConversion.decimetersToInches(pokemon.height.toDouble()))} \"")
            PokemonSpecRow(key = "Weight", value = "${String.format("%.2f",UnitsConversion.hectogramsToPounds(pokemon.weight.toDouble()))} lbs")
            PokemonSpecRow(key = "Order", value = "${pokemon.order}")
        }
    }

}