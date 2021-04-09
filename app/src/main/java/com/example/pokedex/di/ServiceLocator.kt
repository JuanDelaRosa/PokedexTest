package com.example.pokedex.di

import com.example.data.api.NetworkModule
import com.example.data.mappers.PokeApiResponseMapper
import com.example.data.repositories.PokemonRemoteDataSource
import com.example.data.repositories.PokemonRepository

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var pokemonRepository: PokemonRepository? = null

    fun provideBooksRepository(): PokemonRepository {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return pokemonRepository ?: createPokemonRepository()
        }
    }

    private fun createPokemonRepository(): PokemonRepository {
        val newRepo =
            PokemonRepository(
                PokemonRemoteDataSource(
                    networkModule.createBooksApi("https://pokeapi.co/api/v2/"),
                    PokeApiResponseMapper()
                )
            )
        pokemonRepository = newRepo
        return newRepo
    }
}