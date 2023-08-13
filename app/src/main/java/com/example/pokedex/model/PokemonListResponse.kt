package com.example.pokedex.model

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListResponseItem>
)

data class PokemonListResponseItem(
    val name: String,
    val url: String
)
