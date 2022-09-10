package com.codingurkan.rickandmortyapp.repository

import com.codingurkan.rickandmortyapp.service.ApiService
import javax.inject.Inject

class EpisodeListRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun episodeRequest() = apiService.episodeRequest()
}
