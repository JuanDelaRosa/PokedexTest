package com.example.domain.entities

import kotlin.random.Random

data class Pokemon(val id: Int, val name: String, val weight: Int, val height: Int, val sprites: Sprites){

    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)

    fun getURL(): String {
        return sprites.frontDefault
    }
}

