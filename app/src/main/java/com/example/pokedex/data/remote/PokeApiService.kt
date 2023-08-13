package com.example.pokedex.data.remote

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int,
                       @Query("offset") offset: Int): PokemonListResponse
    @GET
    suspend fun getPokemon(@Url url: String): Pokemon
}