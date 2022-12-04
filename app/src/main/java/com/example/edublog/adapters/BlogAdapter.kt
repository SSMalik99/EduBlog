package com.example.edublog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.edublog.R
import com.example.edublog.fragments.BlogFragmentDirections
import com.example.edublog.models.BlogModel
import kotlin.collections.ArrayList

class BlogAdapter(val context: Context, val blogs : ArrayList<BlogModel>)
    : RecyclerView.Adapter<BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_blog_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = blogs[position]

        holder.blogId = blog.id
        holder.blogTitle.text = blog.title
        val content = blog.content


        (if (content.length > 300) {
            content.substring(0, 300) + "..."
        } else {
            content
        }).also { holder.blogContent.text = it }



    }

    override fun getItemCount(): Int {
        return blogs.size
    }
}

class BlogViewHolder(val view : View):RecyclerView.ViewHolder(view){
    var blogId = 0
    var blogTitle = view.findViewById<TextView>(R.id.singleBlogTitle)
    var blogContent = view.findViewById<TextView>(R.id.singleBlogContent)

    init {
        blogTitle.setOnClickListener{
            view.findNavController().navigate(BlogFragmentDirections.actionBlogFragmentToShowFragment(blogId))
        }
    }

}