package com.example.pokedex

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