package com.example.edublog.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

import androidx.navigation.findNavController
import com.example.edublog.R
import com.example.edublog.helpers.DataBaseHelper


class ShowFragment : Fragment() {

    private var blogId = 0
    private lateinit var database : DataBaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        blogId = arguments?.getInt("blogId") ?: 0

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show, container, false)


        database = DataBaseHelper(view.context)
        val blog = database.singleBlog(blogId)

        view.findViewById<TextView>(R.id.showBlogTitle).text = blog.title
        view.findViewById<TextView>(R.id.showBlogContent).text = blog.content
        view.findViewById<TextView>(R.id.showBlogAuthor).text = blog.author
        view.findViewById<TextView>(R.id.showBlogDate).text = blog.date

        view.findViewById<AppCompatButton>(R.id.updateBlogButton).setOnClickListener{
            view.findNavController().navigate(ShowFragmentDirections.actionShowFragmentToUpdateBlogFragment(blogId))

        }
        return view
    }

}