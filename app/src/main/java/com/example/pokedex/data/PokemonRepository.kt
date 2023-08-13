package com.example.pokedex.data

import com.example.pokedex.data.remote.PokeApiClient
import com.example.pokedex.model.PokemonListResponse

class PokemonRepository {
    suspend fun getPokemonList(limit: Int,
                               offset: Int): PokemonListResponse =
         PokeApiClient.pokeApiService.getPokemonList(limit, offset)

    suspend fun getPokemon(url: String) =
        PokeApiClient.pokeApiService.getPokemon(url)
}
