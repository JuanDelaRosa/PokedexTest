package com.example.data.repositories

import com.example.data.api.PokeApiService
import com.example.data.mappers.PokeApiResponseMapper
import com.example.domain.common.Result
import com.example.domain.entities.PokeResponse
import com.example.domain.entities.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRemoteDataSource(private val service: PokeApiService, private val mapper: PokeApiResponseMapper) : IPokemonRemoteDataSource{

    override suspend fun getPokemon(id: Int): Result<Pokemon> =
    withContext(Dispatchers.IO) {
        try {
            val response = service.getPokemonInfo(id)
            if (response.isSuccessful) {
                return@withContext Result.Success(mapper.toPokemon(response.body()!!))
            } else {
                return@withContext Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getPokeList(): Result<PokeResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPokemonList(100,0)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toPokeList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}