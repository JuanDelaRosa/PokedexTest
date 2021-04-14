package com.example.pokedex.ui.pokeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.PokeResult
import com.example.pokedex.databinding.CardPokemonSearchBinding

class PokeListAdapter(val pokemonClick: (Int)-> Unit): RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {

    var pokemonlist: List<PokeResult> = emptyList<PokeResult>()
    lateinit var binding : CardPokemonSearchBinding
    fun setData(list: List<PokeResult>?){
        pokemonlist = list ?: emptyList<PokeResult>()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = CardPokemonSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonlist.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonlist[position]
        holder.binding.pokemonText.text = "#${position +1} - ${pokemon.name}"
        holder.binding.root.setOnClickListener{ pokemonClick(position + 1)}
    }

    class SearchViewHolder(val binding: CardPokemonSearchBinding): RecyclerView.ViewHolder(binding.root)
}