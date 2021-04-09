package com.example.pokedex.Ui.pokeList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.LayoutUtils
import com.example.pokedex.PokedexApplication
import com.example.pokedex.Ui.pokeinfo.PokeInfoActivity
import com.example.pokedex.databinding.PokeListActivityBinding

class PokeListActivity : AppCompatActivity() {

    ///Declaracion del Binding
    private lateinit var binding: PokeListActivityBinding
    private lateinit var  viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///Inicializamos el Binding
        binding = PokeListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //inicializa viewModel

        viewModel = PokeListViewModel.PokeListViewModelFactory((application as PokedexApplication).getPokeListUseCase).create(PokeListViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokelistRecyclerView.adapter = PokeListAdapter{
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }

        viewModel.getPokemonList()
        viewModel.pokelist.observe(this, Observer { pokelist ->
            pokelist?.let {
                (binding.pokelistRecyclerView.adapter as PokeListAdapter).setData(
                    it.results
                )
            }
        })

        viewModel.dataLoading.observe(this, Observer { loading ->
            when (loading) {
                true -> LayoutUtils.crossFade(binding.pbLoading, binding.pokelistRecyclerView)
                false -> LayoutUtils.crossFade(binding.pokelistRecyclerView, binding.pbLoading)
            }
        })

        viewModel.error.observe(this, {
            Toast.makeText(this, "Ocurrio un error: ${it!!}", Toast.LENGTH_SHORT).show()
        })
    }
}