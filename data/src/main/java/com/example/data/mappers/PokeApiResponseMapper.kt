package com.example.data.mappers

import com.example.domain.entities.PokeResponse
import com.example.domain.entities.PokeResult
import com.example.domain.entities.Pokemon
import com.example.domain.entities.Sprites
import com.example.pokedex.model.api.PokeApiResponse
import com.example.pokedex.model.api.PokeResultApi
import com.example.pokedex.model.api.PokemonfromAPI
import com.example.pokedex.model.api.SpritesAPI

class PokeApiResponseMapper {
    fun toPokeList(response: PokeApiResponse): PokeResponse {
        return PokeResponse(
                response.count ?: -1,
            response.next?: "",
            response.previous ?: "",
                mapPokemos(response.results)
        )
    }

    fun toPokemon(response: PokemonfromAPI): Pokemon{
        return Pokemon(
            response.id ?: -1,
            response.name ?: "",
            response.weight ?: -1,
            response.height ?: -1,
                Sprites("https://pokeres.bastionbot.org/images/pokemon/${response.id}.png", "")
                //getSpritesOrDefault(response.sprites)
        )
    }

    /*private fun getSpritesOrDefault(sprites: SpritesAPI?) : Sprites{
        return if(sprites!=null){
            Sprites(
                    sprites.frontDefault ?: "",
                    sprites.frontShiny ?: ""
            )
        }else Sprites("","")
    }*/

    private fun mapPokemos(result: List<PokeResultApi>?) : List<PokeResult>{
        return result?.map {
            PokeResult(
                    it.name ?: "",
                    it.url ?: ""
            )
        } ?: emptyList()
    }

}