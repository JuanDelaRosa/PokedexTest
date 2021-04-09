package com.example.pokedex.Ui.pokeList

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.entities.PokeResponse
import com.example.domain.usecases.GetPokeListUseCase
import kotlinx.coroutines.launch

class PokeListViewModel(private val getPokeListUseCase: GetPokeListUseCase) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _pokelist = MutableLiveData<PokeResponse>()
    val pokelist = _pokelist

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getPokemonList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val pokeResult = getPokeListUseCase.invoke()){
                is Result.Success -> {
                    pokelist.value = pokeResult.data
                    _dataLoading.postValue(false)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    pokelist.value = PokeResponse(0,"","", emptyList())
                    _error.postValue(pokeResult.exception.message)
                }
            }
        }

        ///Retrofit 2.6
        /*CoroutineScope(Dispatchers.IO).launch {
            val response = com.example.data.api.RetrofitAPIClient.pokeApi.getPokemonList(100,0)
            if(response.isSuccessful){
                pokemonList.postValue(response.body()?.results)
            }
        }*/
    }

    class PokeListViewModelFactory(private val getPokeListUseCase: GetPokeListUseCase) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokeListViewModel(getPokeListUseCase) as T
        }
    }
}