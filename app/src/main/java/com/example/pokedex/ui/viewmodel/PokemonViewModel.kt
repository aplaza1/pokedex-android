package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private var limit = 10
    private var offset = 0

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            val response = repository.getPokemonList(limit, offset)
            val newPokemonList = mutableListOf<Pokemon>()
            for (item in response.results) {
                val pokemon: Pokemon = repository.getPokemon(item.url)
                newPokemonList.add(pokemon)
            }
            _pokemonList.value = _pokemonList.value?.plus(newPokemonList) ?: newPokemonList
             offset += limit
        }
    }

}
