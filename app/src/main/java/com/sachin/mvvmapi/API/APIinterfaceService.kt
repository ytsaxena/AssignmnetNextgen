package com.sachin.mvvmapi.API

import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList
import javax.inject.Singleton


interface APIinterfaceService {

    @GET("users/{username}/repos")
    suspend fun getRepoList(@Path("username") username: String): Response<List<RepoResponseModelItem>>

   // https://api.github.com/users/ytsaxena/repos


}