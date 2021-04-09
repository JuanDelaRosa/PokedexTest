package com.example.pokedex.Ui.pokeinfo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pokedex.LayoutUtils
import com.example.pokedex.PokedexApplication
import com.example.pokedex.databinding.PokeinfoActivityBinding
import com.squareup.picasso.Picasso

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: PokeinfoActivityBinding
    private lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokeinfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = PokeInfoViewModel.PokeInfoViewModelFactory((application as PokedexApplication).getPokemonUseCase).create(PokeInfoViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int
        viewModel.getPokemonInfo(id)
        viewModel.pokemon.observe(this, Observer { pokemon ->
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "Altura: ${pokemon.height?.div(10.0)}m"
            binding.weightText.text = "Peso: ${pokemon.weight?.div(10.0)}kg"
            Picasso.with(this).load(pokemon.sprites?.frontDefault).into(binding.imageView)
        })

        viewModel.dataLoading.observe(this, { loading ->
            when (loading) {
                true -> LayoutUtils.crossFade(binding.pbLoading, binding.imageView)
                false -> LayoutUtils.crossFade(binding.imageView, binding.pbLoading)
            }
        })

        viewModel.error.observe(this, {
            Toast.makeText(this, "Ocurrio un error: ${it!!}", Toast.LENGTH_SHORT).show()
        })
    }
}