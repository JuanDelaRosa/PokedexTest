package com.example.pokedex.Ui.pokeList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.api.PokeResult
import com.example.pokedex.service.RetrofitAPIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokeListViewModel : ViewModel() {
    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList() {
        ///Retrofit 2.6
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitAPIClient.pokeApi.getPokemonList(100,0)
            if(response.isSuccessful){
                pokemonList.postValue(response.body()?.results)
            }
        }
    }
}