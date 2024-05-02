package com.reinvy.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reinvy.newsapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var news:List<Article> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        headlineNews()
        allNews()
    }

    private fun headlineNews(){
        val apiInterface : ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call : Call<News> = apiInterface.getNews()
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("HIT_NEWS","Gagal Fetch Data Berita")
                Log.d("HIT_NEWS",t.toString())
            }
            override fun onResponse(call: Call<News>, response: Response<News>) {
                news =  response.body()!!.articles
                Log.d("HIT_News", "News size ${news.size}")
                Picasso.get().load(news.first().urlToImage?:"https://picsum.photos/200/300").into(binding.headlineImage)
                binding.headlineTitle.text = news.first().title
                binding.headlineDesc.text = news.first().description
            }
        })
    }

    private fun allNews(){
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val apiInterface : ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call : Call<News> = apiInterface.getNews()
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("HIT_NEWS","Gagal Fetch Data Berita")
                Log.d("HIT_NEWS",t.toString())
            }
            override fun onResponse(call: Call<News>, response: Response<News>) {
                news =  response.body()!!.articles
                Log.d("HIT_News", "News size ${news.size}")
                binding.recyclerView.adapter = NewsAdapter(news)

            }
        })
    }

}