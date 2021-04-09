package com.example.pokedex

import android.app.Application
import com.example.domain.repositories.IPokemonRepository
import com.example.domain.usecases.GetPokeListUseCase
import com.example.domain.usecases.GetPokemonUseCase
import timber.log.Timber

class PokedexApplication : Application() {
    private val pokemonRepository: IPokemonRepository
        get() = ServiceLocator.provideBooksRepository()

    val getPokeListUseCase: GetPokeListUseCase
        get() = GetPokeListUseCase(pokemonRepository)

    val getPokemonUseCase: GetPokemonUseCase
        get() = GetPokemonUseCase(pokemonRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}