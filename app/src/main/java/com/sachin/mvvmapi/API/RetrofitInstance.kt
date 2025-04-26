package com.sachin.mvvm2.API

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



//object RetrofitInstance {
//
//    private const val BASE_URL = "https://api.thecatapi.com/v1/images/"
//
//    // Create the logging interceptor
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    // Create OkHttpClient and add the logging interceptor
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .build()
//
//    // Create Retrofit instance
//    val api : APIinterfaceService by lazy {
//
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)  // Pass the OkHttpClient with interceptor
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(APIinterfaceService::class.java)
//    }
//}