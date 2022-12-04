package com.example.edublog.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.edublog.R
import com.example.edublog.helpers.DataBaseHelper

class UpdateBlogFragment : Fragment() {

    private var blogId = 0
    private lateinit var database : DataBaseHelper
    private lateinit var editTitle :EditText
    private lateinit var editContent :EditText
    private lateinit var editAuthor :EditText
    private lateinit var errorMessageView :TextView
    private lateinit var errorMessge: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        blogId = arguments?.getInt("blogId") ?: 0
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_blog, container, false)
        database = DataBaseHelper(view.context)

// find text view and edit text views
        editTitle = view.findViewById(R.id.updateBlogTitle)
        editContent =view.findViewById(R.id.updateBlogContent)
        editAuthor =view.findViewById(R.id.updateBlogAuthor)
        errorMessageView = view.findViewById(R.id.errorMessageUpdating)

        var blog = database.singleBlog(blogId)

        editContent.text = createEditableFromContent(blog.content)
        editTitle.text = createEditableFromContent(blog.title)
        editAuthor.text = createEditableFromContent(blog.author)

        view.findViewById<AppCompatButton>(R.id.updateCurrentBlogButton).setOnClickListener{

            errorMessageView.visibility = View.GONE

            val result = database.updateBlog(
                blogId,
                editTitle.text.toString(),
                editContent.text.toString(),
                editAuthor.text.toString()
            )

            if( result) {
                errorMessageView.visibility = View.VISIBLE
                val successMessage = "Blog Update Successfully"
                Toast.makeText(view.context,successMessage, Toast.LENGTH_LONG).show()
                errorMessageView.text = successMessage
                errorMessageView.setTextColor(R.color.success!!)

            }else{
                errorMessageView.text = errorMessge
            }

        }
        return view;
    }

    private fun createEditableFromContent(content:String) : Editable {
        return Editable.Factory.getInstance().newEditable(content)
    }

    private  fun validateInputs(): Boolean{
        var error = false
        if (editTitle.text.toString().trim() == ""){
            errorMessge = "Blog Title is Required"
            error = true
        }else if (editContent.text.toString().trim() == ""){
            errorMessge = "Blog Content is Required"
            error = true
        }else if (editAuthor.text.toString().trim() == "") {
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