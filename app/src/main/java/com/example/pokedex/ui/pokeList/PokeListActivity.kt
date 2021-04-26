package com.example.pokedex.ui.pokeList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.LayoutUtils
import com.example.pokedex.PokedexApplication
import com.example.pokedex.databinding.PokeListActivityBinding
import com.example.pokedex.ui.pokeinfo.PokeinfoActivity

class PokeListActivity : AppCompatActivity() {

    private val binding: PokeListActivityBinding by lazy { PokeListActivityBinding.inflate(layoutInflater) }
    private val viewModel: PokeListViewModel by lazy{ PokeListViewModel.PokeListViewModelFactory((application as PokedexApplication)).create(PokeListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokelistRecyclerView.adapter = PokeListAdapter{
            val intent = Intent(this, PokeinfoActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }

        viewModel.getPokemonList()
        viewModel.pokelist.observe(this, { pokelist ->
            pokelist?.let {
                (binding.pokelistRecyclerView.adapter as PokeListAdapter).setData(
                    it.results
                )
            }
        })

        viewModel.dataLoading.observe(this, { loading ->
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