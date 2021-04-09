package com.example.data.mappers

import com.example.domain.entities.PokeResponse
import com.example.domain.entities.PokeResult
import com.example.domain.entities.Pokemon
import com.example.domain.entities.Sprites
import com.example.pokedex.model.api.PokeApiResponse
import com.example.pokedex.model.api.PokemonfromAPI

class PokeApiResponseMapper {
    fun toPokeList(response: PokeApiResponse): PokeResponse {
        return PokeResponse(
            response.count,
            response.next,
            response.previous,
            response.results?.map {
                PokeResult(
                    it.name,
                    it.url
                )
            }
        )
    }

    fun toPokemon(response: PokemonfromAPI): Pokemon{
        return Pokemon(
            response.id,
            response.name,
            response.weight,
            response.height,
            Sprites(
                response.sprites?.frontDefault,
                response.sprites?.frontDefault
            )
        )
    }
}