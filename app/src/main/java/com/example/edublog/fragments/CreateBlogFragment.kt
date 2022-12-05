package com.example.edublog.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.edublog.R
import com.example.edublog.helpers.DataBaseHelper
import com.mobsandgeeks.saripaar.annotation.*


class CreateBlogFragment : Fragment() {

    private lateinit var dateBase: DataBaseHelper

    @NotEmpty
    private lateinit var blogTitle: EditText

    @NotEmpty
    private lateinit var blogContent: EditText

    @NotEmpty
    private lateinit var blogAuthor : EditText

    private lateinit var errorMessageView: TextView
    private lateinit var errorMessge: String

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_blog, container, false)
        dateBase = DataBaseHelper(view.context)

        blogTitle = view.findViewById<EditText>(R.id.blogTitle)
        blogContent = view.findViewById<EditText>(R.id.blogContent)
        blogAuthor = view.findViewById<EditText>(R.id.blogAuthor)
        errorMessageView = view.findViewById(R.id.errorMessage)

        view.findViewById<Button>(R.id.saveBlog).setOnClickListener{
            errorMessageView.visibility = View.GONE
            if(!validateInputs()){
                errorMessageView.text = errorMessge
                errorMessageView.visibility = View.VISIBLE
            }else{
                val result = dateBase.createBlog(
                    title = blogTitle.text.toString(),
                    content = blogContent.text.toString(),
                    author = blogAuthor.text.toString()
                )

                if (result) {
                    view.findNavController().navigate(R.id.action_createBlogFragment_to_blogFragment)
                }else{
                    errorMessageView.text = "Something went wrong, plese try later!"
                    errorMessageView.visibility = View.VISIBLE
                }
            }

        }

        return view
    }

    private  fun validateInputs(): Boolean{
        var error = false
        if (blogTitle.text.toString().trim() == ""){
            errorMessge = "Blog Title is Required"
            error = true
        }else if (blogContent.text.toString().trim() == ""){
            errorMessge = "Blog Content is Required"
            error = true
        }else if (blogAuthor.text.toString().trim() == "") {
            errorMessge = "Blog Author Name is Required"
            error = true
        }

        if (error){
            Toast.makeText(this.view?.context, errorMessge, Toast.LENGTH_LONG).show()
//            errorMessageView.text = errorMessage

            return false
        }


        return true
    }



}