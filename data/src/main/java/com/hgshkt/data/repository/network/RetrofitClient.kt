package com.hgshkt.data.repository.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val networkInterceptor: NetworkInterceptor
) {
    private val baseUrl = "https://pokeapi.co/"


    val pokemonClient by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}