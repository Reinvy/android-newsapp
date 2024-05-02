package com.reinvy.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reinvy.newsapp.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private val articles: List<Article>):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() : Int {
        return articles.size
    }

    class NewsViewHolder (private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.newsTitle.text = article.title
            binding.newsDesc.text = article.description
            Picasso.get().load(article.urlToImage?:"https://picsum.photos/200/300").into(binding.newsImage);

        }
    }
}