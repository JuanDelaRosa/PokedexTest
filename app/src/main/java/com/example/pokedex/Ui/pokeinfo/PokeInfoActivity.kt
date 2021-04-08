package com.example.pokedex.Ui.pokeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.PokeinfoActivityBinding
import com.squareup.picasso.Picasso

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: PokeinfoActivityBinding
    lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokeinfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int
        viewModel.getPokemonInfo(id)
        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "Altura: ${pokemon.height/10.0}m"
            binding.weightText.text = "Peso: ${pokemon.weight/10.0}kg"
            Picasso.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
        })
    }
}