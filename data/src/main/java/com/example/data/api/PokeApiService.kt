package com.example.data.api

import com.example.pokedex.model.api.PokeApiResponse
import com.example.pokedex.model.api.PokemonfromAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id:Int): Response<PokemonfromAPI>
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit:Int, @Query("offset") offset: Int): Response<PokeApiResponse>

}