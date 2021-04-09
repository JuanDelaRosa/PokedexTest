package com.example.pokedex.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonfromAPI(
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("weight") val weight: Int?,
    @Expose @SerializedName("height") val height: Int?,
    @Expose @SerializedName("sprites") val sprites: SpritesAPI?,

    )

data class SpritesAPI(
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?
)

