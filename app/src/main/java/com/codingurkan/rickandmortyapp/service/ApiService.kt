package com.codingurkan.rickandmortyapp.service

import com.codingurkan.rickandmortyapp.model.CharactersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun characterRequest(
        @Query("next") next : String,
        @Query("prev") prev : String,
        @Query("pages") pages : Int
    ) : Response<CharactersResponseModel>

    @GET("episode")
    suspend fun episodeRequest(
        //@Query("next") next : String,
        //@Query("prev") prev : String,
        //@Query("pages") pages : Int
    ) : Response<CharactersResponseModel>
}