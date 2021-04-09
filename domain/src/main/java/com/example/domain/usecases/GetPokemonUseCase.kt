package com.example.domain.usecases

import com.example.domain.repositories.IPokemonRepository

class GetPokemonUseCase(private val pokeRepository: IPokemonRepository) {
    suspend operator fun invoke(id : Int) = pokeRepository.getPokemon(id)
}