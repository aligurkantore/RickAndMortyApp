package com.codingurkan.rickandmortyapp.ui.fragments.episodelist

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingurkan.rickandmortyapp.model2.EpisodeResponseModel
import com.codingurkan.rickandmortyapp.repository.EpisodeListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(private val repository : EpisodeListRepository ) : ViewModel() {

    private var job : Job? = null
    val episodeList = MutableLiveData<EpisodeResponseModel>()

    fun downloadEpisodes(){
        job = viewModelScope.launch(Dispatchers.IO){
            val response = repository.episodeRequest()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let { _data ->
                        episodeList.postValue(_data)
                    }
                }
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}