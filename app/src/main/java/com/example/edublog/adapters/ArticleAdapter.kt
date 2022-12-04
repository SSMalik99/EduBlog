package com.example.edublog.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edublog.models.ArticleModel
import com.example.edublog.R


class ArticleAdapter(val context: Context, var articles : ArrayList<ArticleModel>)
: RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_article_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        holder.author.text = article.author
        holder.content.text = article.content
        Glide.with(holder.view.context).load(article.imageUrl).into(holder.imageUrl)
        holder.date.text = article.date
        holder.readMoreUrl = article.readMoreUrl
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: ArrayList<ArticleModel>){
        articles = newArticles
    }
}

class ArticleViewHolder(val view : View):RecyclerView.ViewHolder(view){
    private var layoutContainer : LinearLayout = view.findViewById<LinearLayout>(R.id.articleViewContainer)
    var title : TextView = view.findViewById<TextView>(R.id.articleTitle)
    var author:TextView = view.findViewById<TextView>(R.id.articleAuthor)
    var content: TextView = view.findViewById<TextView>(R.id.articleContent)
    var date: TextView = view.findViewById<TextView>(R.id.articleDate)
    var imageUrl : ImageView = view.findViewById<ImageView>(R.id.articleImage)
    var readMoreUrl = ""

    init {
        layoutContainer.setOnClickListener{
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(readMoreUrl))
//            ContextCompat.startActivity(view.context, browserIntent, null)
            Log.i("urrrrrl", readMoreUrl)
        }
    }
}