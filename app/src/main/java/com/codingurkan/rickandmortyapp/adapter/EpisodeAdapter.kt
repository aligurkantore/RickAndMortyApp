package com.codingurkan.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.rickandmortyapp.databinding.EpisodeListItemBinding
import com.codingurkan.rickandmortyapp.model2.Results

class EpisodeAdapter(private val episodeList : List<Results>,
                     private val clickListener : ClickListener) : RecyclerView.Adapter<EpisodeAdapter.EpisodeVH>() {

    inner class EpisodeVH(val binding : EpisodeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeVH {
        val view = EpisodeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EpisodeVH(view)
    }

    override fun onBindViewHolder(holder: EpisodeVH, position: Int) {
        with(holder.binding){
            val data = episodeList[position]
            episode.text = data.episode
            episodeName.text = data.name
            episodeAirDate.text = data.air_date
            episodeCardView.setOnClickListener {
                clickListener.onClick(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }
    interface ClickListener{
        fun onClick(data : Results)
    }
}