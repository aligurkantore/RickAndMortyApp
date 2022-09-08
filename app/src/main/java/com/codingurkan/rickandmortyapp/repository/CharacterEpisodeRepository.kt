package com.codingurkan.rickandmortyapp.repository

import com.codingurkan.rickandmortyapp.service.ApiService
import javax.inject.Inject

class CharacterEpisodeRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun characterEpisodeRequest() = apiService.episodeRequest()
}