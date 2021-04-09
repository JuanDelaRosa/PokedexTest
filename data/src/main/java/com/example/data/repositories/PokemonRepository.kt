package com.example.data.repositories

import com.example.domain.common.Result
import com.example.domain.entities.PokeResponse
import com.example.domain.entities.Pokemon
import com.example.domain.repositories.IPokemonRepository

class PokemonRepository(private val remoteDataSource: PokemonRemoteDataSource ) : IPokemonRepository {
    override suspend fun getPokeList(): Result<PokeResponse> {
        return remoteDataSource.getPokeList()
    }
    override suspend fun getPokemon(id: Int): Result<Pokemon> {
        return remoteDataSource.getPokemon(id)
    }
}