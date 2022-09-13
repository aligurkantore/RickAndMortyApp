package com.codingurkan.rickandmortyapp.model2

import java.io.Serializable

data class Results(
    val air_date: String? = null,
    val characters: List<String>? = null,
    val created: String? = null,
    val episode: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val url: String? = null
) : Serializable