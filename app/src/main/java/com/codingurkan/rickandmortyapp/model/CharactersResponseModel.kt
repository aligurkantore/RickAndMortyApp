package com.codingurkan.rickandmortyapp.model

import java.io.Serializable

data class CharactersResponseModel(
    val info: İnfo,
    val results: List<Result>
)