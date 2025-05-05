package com.sachin.mvvmapi.API

import androidx.room.Query
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.Fragments.Home.model.NewsResponseModel
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import retrofit2.Response
import retrofit2.http.GET


interface APIinterfaceService {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @retrofit2.http.Query("country") country: String,
        @retrofit2.http.Query("q") query: String,
           @retrofit2.http.Query("apiKey") apiKey: String ="d08a912c270a4ab0af3b6dae3512985c"

    ): Response<NewsResponseModel>

  //  https://newsapi.org/v2/top-headlines?q=cnn&apiKey=d08a912c270a4ab0af3b6dae3512985c

    // https://api.github.com/users/ytsaxena/repos
    // d08a912c270a4ab0af3b6dae3512985c

//    , @retrofit2.http.Query("page") page: Int =,
//    @retrofit2.http.Query("pageSize") pageSize: Int
}

