package com.codingurkan.rickandmortyapp.service

import com.codingurkan.rickandmortyapp.model.CharactersResponseModel
import com.codingurkan.rickandmortyapp.model2.EpisodeResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    suspend fun characterRequest(
        @Query("page") pages : Int
    ) : Response<CharactersResponseModel>

    @GET("episode/")
    suspend fun episodeRequest() : Response<EpisodeResponseModel>
}