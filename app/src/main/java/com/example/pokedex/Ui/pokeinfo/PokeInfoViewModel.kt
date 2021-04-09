package com.example.pokedex.Ui.pokeinfo

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.entities.Pokemon
import com.example.domain.entities.Sprites
import com.example.domain.usecases.GetPokemonUseCase
import kotlinx.coroutines.launch

class PokeInfoViewModel(private val getPokemonUseCase: GetPokemonUseCase) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon = _pokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getPokemonInfo(id: Int){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val pokeResult = getPokemonUseCase.invoke(id)){
                is Result.Success -> {
                    pokemon.value = pokeResult.data
                    _dataLoading.postValue(false)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
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
            return PokeInfoViewModel(getPokemonUseCase) as T
        }
    }
}