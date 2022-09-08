package com.codingurkan.rickandmortyapp.ui.fragments.characterdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingurkan.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.codingurkan.rickandmortyapp.model.Result
import com.codingurkan.rickandmortyapp.utils.loadImage

class CharacterDetailsFragment : Fragment() {

    private var binding : FragmentCharacterDetailsBinding? = null
    private var data : Result? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getSerializable("details") as Result
        initializeListeners()
    }
    private fun initializeListeners(){
        data?.image?.let { binding?.detailsImage?.loadImage(it)}
        binding?.detailsName?.text = data?.name
        binding?.detailsGender?.text = data?.gender
        binding?.detailsSpecies?.text = data?.species
    }
}