package com.sachin.mvvm2.CatList.Repository

import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.API.APIinterfaceService
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.Fragments.Home.model.NewsResponseModel
import com.sachin.mvvmapi.Room.RepoDAO
import com.sachin.mvvmapi.Utility.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeRepository @Inject constructor(
    val apiInterface: APIinterfaceService, val repoDao: RepoDAO, val networkHelper: NetworkHelper
) {

    suspend fun getNewsAPI(username: String): Resource<NewsResponseModel> {
        return try {
            val response = apiInterface.getNews("us")
            if (response.isSuccessful) {
                // Return success
                val data = response.body()
                // Handle null case properly
                if (data != null) {
                    // Removed unnecessary withContext since we're already handling this properly
                    Resource.Success(data)
                } else {
                    Resource.Error("Empty response body")
                }
            } else {
                // Return error with more specific information
                Resource.Error("API call failed with code: ${response.code()}")
            }
        } catch (e: Exception) {
            return Resource.Error("An error occurred: ${e.message}")
        }
    }

/*
    suspend fun getNewsAPI(username: String): Resource<List<Article>> {

        return try {
            val response = apiInterface.getNews("us")
            if (response.isSuccessful) {
                // Return success
                withContext(Dispatchers.IO) {
                    val data = response.body()
                    //  repoDao.insertRepos(data)
                    //  Log.d("RepoListRepository", "Inserting data into DB: $data")

                    Resource.Success(data)

                }
            } else {
                // Return error
                return Resource.Error("API call failed with code: ${response}")
            }


        } catch (e: Exception) {
            return Resource.Error("An error occurred: ${e.message}")
        }

    }
*/


}

