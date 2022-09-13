package com.codingurkan.rickandmortyapp.ui.fragments.episodelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.adapter.EpisodeAdapter
import com.codingurkan.rickandmortyapp.databinding.FragmentEpisodeListBinding
import com.codingurkan.rickandmortyapp.model2.Results
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeListFragment : Fragment() {

    private var binding : FragmentEpisodeListBinding? = null
    private var viewModel : EpisodeListViewModel? = null
    private var adapter : EpisodeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEpisodeListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initObserver()
        viewModel?.downloadEpisodes()
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[EpisodeListViewModel::class.java]
    }
    private fun initObserver(){
        viewModel?.episodeList?.observe(viewLifecycleOwner){
            it?.resultsses?.let(::initAdapter)
        }
    }
    private fun initAdapter(data : List<Results>){
        adapter = EpisodeAdapter(data , object : EpisodeAdapter.ClickListener{
            override fun onClick(data: Results) {
                val bundle = Bundle()
                bundle.putSerializable("episodeDetails",data)
                findNavController().navigate(R.id.action_episodeListFragment_to_episodeDetailsFragment)
            }

        })
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        }
    }
}