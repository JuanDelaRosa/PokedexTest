package com.example.pokedex.Ui.pokeList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)

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
        viewModel.pokemonList.observe(this, Observer { list ->
            (binding.pokelistRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }
}