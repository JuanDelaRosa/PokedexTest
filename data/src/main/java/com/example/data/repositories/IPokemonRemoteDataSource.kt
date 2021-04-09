package com.example.data.repositories

import com.example.domain.common.Result
import com.example.domain.entities.PokeResponse
import com.example.domain.entities.Pokemon

interface IPokemonRemoteDataSource  {
    suspend fun getPokeList(): Result<PokeResponse>
    suspend fun getPokemon(id : Int): Result<Pokemon>
}