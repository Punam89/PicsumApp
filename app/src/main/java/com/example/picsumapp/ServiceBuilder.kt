package com.example.picsumapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {

    private val client= OkHttpClient.Builder().build()
    private val BASE_URL="https://picsum.photos/list/"
    var IMAGE_URL="https://picsum.photos/300/300?image="

    private val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun<T> buildSerices(service: Class<T>): T{
        return retrofit.create(service)
    }

}