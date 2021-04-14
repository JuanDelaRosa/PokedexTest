package com.example.pokedex.ui.pokeinfo

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.entities.Pokemon
import com.example.domain.entities.Sprites
import com.example.domain.usecases.GetPokemonUseCase
import kotlinx.coroutines.launch

class PokeinfoViewModel(private val getPokemonUseCase: GetPokemonUseCase) : ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisibility

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon = _pokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getPokemonInfo(id: Int){
        ///In this view i use Coroutines and live data to observe
        viewModelScope.launch {
            _progressVisibility.value = true
            when (val pokeResult = getPokemonUseCase.invoke(id)){
                is Result.Success -> {
                    pokemon.value = pokeResult.data
                    _progressVisibility.value = false
                }
                is Result.Error -> {
                    _progressVisibility.value = false
                    pokemon.value = Pokemon(0,"",1,1, Sprites("",""))
                    _error.postValue(pokeResult.exception.message)
                }
            }
        }
        ///Retrofit 2.6
        /*CoroutineScope(Dispatchers.IO).launch {
            //Comunicacion entre hilos
            //withContext(Dispatchers.Main){}
            val response = com.example.data.api.RetrofitAPIClient.pokeApi.getPokemonInfo(id)
            if(response.isSuccessful){
                pokemonInfo.postValue(response.body())
            }
        }*/
    }

    class PokeInfoViewModelFactory(private val getPokemonUseCase: GetPokemonUseCase) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokeinfoViewModel(getPokemonUseCase) as T
        }
    }
}