package com.example.domain.usecases

import com.example.domain.repositories.IPokemonRepository

class GetPokeListUseCase(private val pokeRepository: IPokemonRepository) {
    suspend operator fun invoke() = pokeRepository.getPokeList()
}