package com.codingurkan.rickandmortyapp.model

import java.io.Serializable

data class Episode(
    val id : Int? = null,
    val name : String,
    val air_date : String,
    val created : String,
    val characters : String
) : Serializable