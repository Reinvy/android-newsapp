package com.reinvy.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        fun getClient(): Retrofit {
            val baseUrl = "https://newsapi.org/v2/"

            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}