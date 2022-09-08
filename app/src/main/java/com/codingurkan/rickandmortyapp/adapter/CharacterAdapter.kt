package com.codingurkan.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.rickandmortyapp.databinding.CharacterListItemBinding
import com.codingurkan.rickandmortyapp.databinding.CharactersListItemBinding
import com.codingurkan.rickandmortyapp.model.Result
import com.codingurkan.rickandmortyapp.utils.loadImage

class CharacterAdapter(private val characterList : List<Result>,
private val itemClickListener : ItemClickListener) : RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    class CharacterVH(val binding : CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterVH(view)
    }
    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        with(holder.binding){
            val data = characterList[position]
            characterImage.loadImage(characterList[position].image)
            characterName.text = data.name
            characterImage.setOnClickListener {
                itemClickListener.onClick(data)
            }
        }
    }
    override fun getItemCount(): Int {
        return characterList.size
    }
    interface ItemClickListener{
        fun onClick(data : Result)
    }
}