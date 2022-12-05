package com.example.edublog.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edublog.R
import com.example.edublog.adapters.BlogAdapter
import com.example.edublog.helpers.DataBaseHelper

class BlogFragment : Fragment() {

    private  lateinit var database : DataBaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blog, container, false)

        database = DataBaseHelper(view.context)

        val blogs = database.allBlogs()

        if (blogs.size < 1){
            val hiddenText = view.findViewById<TextView>(R.id.emptyListView)
            hiddenText.visibility=View.VISIBLE
            hiddenText.text = "You haven't created any blog, Start Publishing Your educational blogs."

        }else{
            val recyclerView = view.findViewById<RecyclerView>(R.id.listBlogViewRecycler)
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            val blogAdapter = BlogAdapter(view.context, blogs)
            recyclerView.adapter = blogAdapter

        }


        return view
    }

}