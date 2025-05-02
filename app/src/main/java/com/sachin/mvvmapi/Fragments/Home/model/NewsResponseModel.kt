package com.sachin.mvvmapi.Fragments.Home.model

data class NewsResponseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)