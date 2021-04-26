package com.example.pokedex

import com.example.data.api.NetworkModule
import com.example.data.mappers.PokeApiResponseMapper
import com.example.data.repositories.PokemonRemoteDataSource
import com.example.data.repositories.PokemonRepository
import com.example.domain.repositories.IPokemonRepository

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var pokemonRepository: IPokemonRepository? = null

    fun providePokeRepository(): IPokemonRepository {
        synchronized(this) {
            return pokemonRepository ?: createPokemonRepository()
        }
    }

    private fun createPokemonRepository(): IPokemonRepository {
        val newRepo =
            PokemonRepository(
                PokemonRemoteDataSource(
                    networkModule.createPokeAPI("https://pokeapi.co/api/v2/"),
                    PokeApiResponseMapper()
                )
            )
        pokemonRepository = newRepo
        return newRepo
    }
}