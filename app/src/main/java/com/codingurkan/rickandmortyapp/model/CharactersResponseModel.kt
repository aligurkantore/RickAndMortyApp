package com.codingurkan.rickandmortyapp.model

data class CharactersResponseModel(
    val info: Info,
    val results: List<Result>
)