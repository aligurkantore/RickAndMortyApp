package com.codingurkan.rickandmortyapp.ui.fragments.characterslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.adapter.CharacterAdapter
import com.codingurkan.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.codingurkan.rickandmortyapp.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private var adapter: CharacterAdapter? = null
    private var viewModel: CharactersListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCharacterListBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViewModel()
        initObserver()
        initializePageNumber()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CharactersListViewModel::class.java]
    }
    private fun initObserver() {
        viewModel?.apply {
            charactersList.observe(viewLifecycleOwner) { _data ->
                if (pageNumber.value == _data.info.count/20){
                    binding?.btnNext?.visibility= INVISIBLE
                }else{
                    binding?.btnNext?.visibility= VISIBLE
                }
                _data?.results?.let (::initAdapter)
            }
            pageNumber.observe(viewLifecycleOwner) { _pageNumber ->
                _pageNumber?.let (::downloadCharacters)
                if (_pageNumber == 1) {
                    binding?.apply {
                        btnBack.visibility = View.INVISIBLE
                        tvPage.text = _pageNumber.toString()
                    }
                } else {
                    binding?.apply {
                        btnBack.visibility = View.VISIBLE
                        tvPage.text = _pageNumber.toString()
                    }
                }
            }
        }
    }
    private fun initializePageNumber(){
        viewModel?.apply {
            binding?.apply {
                btnBack.setOnClickListener {
                    pageNumber.postValue( pageNumber.value?.plus(-1))
                }
                btnNext.setOnClickListener {
                    pageNumber.postValue( pageNumber.value?.plus(1))
                }
            }
        }
    }
   private fun initAdapter(data: List<Result>) {
        adapter = CharacterAdapter(data, object : CharacterAdapter.ItemClickListener {
            override fun onClick(data: Result) {
                val bundle = Bundle()
                bundle.putSerializable("details",data)
                findNavController().navigate(R.id.action_charactersListFragment_to_characterDetailsFragment,bundle)
            }
        })
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }
    }
}