package com.codingurkan.rickandmortyapp.ui.fragments.characterepisodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.databinding.FragmentCharacterEpisodesBinding


class CharacterEpisodesFragment : Fragment() {

    private var binding : FragmentCharacterEpisodesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCharacterEpisodesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}