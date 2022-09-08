package com.codingurkan.rickandmortyapp.ui.fragments.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingurkan.rickandmortyapp.model.CharactersResponseModel
import com.codingurkan.rickandmortyapp.repository.CharactersListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val repository: CharactersListRepository ) : ViewModel() {

    private var job : Job? = null
    val charactersList =MutableLiveData<CharactersResponseModel>()
    val pageNumber = MutableLiveData<Int>().also { it.value = 1 }

    /*
    init {
        downloadCharacters(1,"","")
    }

     */

    fun downloadCharacters(pages : Int){
        job = viewModelScope.launch(Dispatchers.IO){
            val response = repository.charactersListRequest(pages)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let { _data ->
                        charactersList.postValue(_data)
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