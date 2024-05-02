package com.reinvy.newsapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("everything?q=indonesia&apiKey=97e09c8c24b84aa7a3529539a2536de8")
    fun getNews(): Call<News>

    @GET("top-headlines?country=us&apiKey=97e09c8c24b84aa7a3529539a2536de8")
    fun getHeadline(): Call<News>
}