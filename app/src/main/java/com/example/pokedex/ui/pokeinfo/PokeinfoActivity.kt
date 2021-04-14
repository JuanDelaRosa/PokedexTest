package com.example.pokedex.ui.pokeinfo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.PokedexApplication
import com.example.pokedex.databinding.PokeinfoActivityBinding


class PokeinfoActivity : AppCompatActivity() {
    private val viewModel: PokeinfoViewModel by lazy { PokeinfoViewModel.PokeInfoViewModelFactory((application as PokedexApplication).getPokemonUseCase).create(PokeinfoViewModel::class.java) }
    private lateinit var binding : PokeinfoActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokeinfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int

        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.getPokemonInfo(id)
        viewModel.error.observe(this, {
            Toast.makeText(this, "Ocurrio un error: ${it!!}", Toast.LENGTH_SHORT).show()
        })
    }
}