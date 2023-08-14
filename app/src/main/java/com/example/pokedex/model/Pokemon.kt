package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,
    val order: Int,
    val id: Int,
    val sprites: Sprites,
    val types: List<TypeItem>
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String
)
