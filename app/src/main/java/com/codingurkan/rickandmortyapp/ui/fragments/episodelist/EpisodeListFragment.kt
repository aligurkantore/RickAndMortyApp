package com.codingurkan.rickandmortyapp.ui.fragments.episodelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.databinding.FragmentEpisodeListBinding

class EpisodeListFragment : Fragment() {

    private var binding : FragmentEpisodeListBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEpisodeListBinding.inflate(layoutInflater)
        return binding?.root
    }
}