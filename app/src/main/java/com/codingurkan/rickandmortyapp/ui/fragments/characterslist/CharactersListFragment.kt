package com.codingurkan.rickandmortyapp.ui.fragments.characterslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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
        initViewModel()
        initObserver()
        initializePageNumber()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CharactersListViewModel::class.java]
    }
    private fun initObserver() {
        viewModel?.apply {
            charactersList.observe(viewLifecycleOwner) {
                it?.let {
                    initAdapter(it.results)
                }
            }
            nextPage.observe(viewLifecycleOwner){ _nextPage ->
                pageNumber.value?.let { _pageNumber ->
                    viewModel?.downloadCharacters(_nextPage.toString(),prevPage.toString(),_pageNumber)
                }
            }
            prevPage.observe(viewLifecycleOwner){ _prevPage ->
                pageNumber.value?.let { _pageNumber ->
                    viewModel?.downloadCharacters(nextPage.toString(),_prevPage.toString(),_pageNumber)
                }
            }
            pageNumber.observe(viewLifecycleOwner) { _pageNumber ->
                if (_pageNumber == 1) {
                    binding?.apply {
                        btnBack.visibility = View.INVISIBLE
                        tvPage.text = _pageNumber.toString()
                    }
                    nextPage.value?.let {
                        downloadCharacters(nextPage.toString(), prevPage.toString(),_pageNumber)
                    }
                    prevPage.value?.let {
                        downloadCharacters(nextPage.toString(), prevPage.toString(),_pageNumber)
                    }
                } else {
                    binding?.apply {
                        btnBack.visibility = View.VISIBLE
                        tvPage.text = _pageNumber.toString()
                    }
                    nextPage.value?.let {
                        downloadCharacters(nextPage.toString(), prevPage.toString(),_pageNumber)
                    }
                    prevPage.value?.let {
                        downloadCharacters(nextPage.toString(), prevPage.toString(),_pageNumber)
                    }

                }
            }
        }
    }
    private fun initializePageNumber(){
        viewModel?.apply {
            binding?.apply {
                btnBack.setOnClickListener {
                    pageNumber.value = pageNumber.value?.plus(-1)
                }
                btnNext.setOnClickListener {
                    pageNumber.value = pageNumber.value?.plus(1)
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