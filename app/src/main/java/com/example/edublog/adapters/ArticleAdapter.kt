package com.example.edublog.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edublog.models.ArticleModel


class ArticleAdapter(val context: Context, val articles : ArrayList<ArticleModel>)
: RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class ArticleViewHolder(val view : View):RecyclerView.ViewHolder(view){}