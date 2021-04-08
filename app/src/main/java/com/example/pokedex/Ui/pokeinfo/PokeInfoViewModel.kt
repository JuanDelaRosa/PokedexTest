package com.example.pokedex.Ui.pokeinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.api.Pokemon
import com.example.pokedex.service.RetrofitAPIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokeInfoViewModel : ViewModel() {
    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int){
        ///Retrofit 2.6
        CoroutineScope(Dispatchers.IO).launch {
            //Comunicacion entre hilos
            //withContext(Dispatchers.Main){}
            val response = RetrofitAPIClient.pokeApi.getPokemonInfo(id)
            if(response.isSuccessful){
                pokemonInfo.postValue(response.body())
            }
        }
    }
}