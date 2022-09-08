package com.codingurkan.rickandmortyapp.repository

import com.codingurkan.rickandmortyapp.service.ApiService
import javax.inject.Inject

class CharactersListRepository @Inject constructor(private val apiService : ApiService) {
    suspend fun charactersListRequest(next : String, prev : String,pages : Int) =
        apiService.characterRequest("null","null",pages)
}