package com.sachin.mvvm2.CatList.Repository

import android.util.Log
import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.API.APIinterfaceService
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import com.sachin.mvvmapi.Room.RepoDAO
import com.sachin.mvvmapi.Utility.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepoListRepository @Inject constructor(
    val apiInterface: APIinterfaceService, val repoDao: RepoDAO, val networkHelper: NetworkHelper
) {

//    suspend fun getRepoListAPI(username: String): Resource<List<RepoResponseModelItem>> {
//
//        return try {
//            if (networkHelper.isNetworkConnected()) {
//                val response = apiInterface.getRepoList(username)
//                if (response.isSuccessful) {
//                    // Return success
//                    withContext(Dispatchers.IO) {
//                        val data = response.body() ?: arrayListOf()
//                        repoDao.insertRepos(data)
//                        Log.d("RepoListRepository", "Inserting data into DB: $data")
//
//                        Resource.Success(data)
//
//                    }
//
//                } else {
//                    // Return error
//                    return Resource.Error("API call failed with code: ${response}")
//                }
//
//            } else {
//             val cachedData = repoDao.getAllRepos()
////                Resource.Success(cachedData)
//
//                //    repoDao.getAllRepos()
//                    Log.d("RepoListRepository", "Fetched cached data: $cachedData")
//                    Resource.Success(cachedData)
//            }
//
//
//        } catch (e: Exception) {
//            return Resource.Error("An error occurred: ${e.message}")
//        }
//
//    }


}

