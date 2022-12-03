package com.example.edublog.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.edublog.R
import com.example.edublog.databinding.ActivityMainBinding
import com.example.edublog.helpers.DataBaseHelper
import com.example.edublog.models.BlogModel
import jp.wasabeef.richeditor.RichEditor

class CreateBlogFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dateBase: DataBaseHelper

    private lateinit var blogTitle: EditText
    private lateinit var blogContent: EditText
    private lateinit var blogAuthor : EditText
    private lateinit var errorMessageView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_blog, container, false)

        dateBase = DataBaseHelper(view.context)

        blogTitle = view.findViewById<EditText>(R.id.blogTitle)
        blogContent = view.findViewById<EditText>(R.id.blogContent)
        blogAuthor = view.findViewById<EditText>(R.id.blogAuthor)
//        errorMessageView = view.findViewById(R.id.errorMessage)

        view.findViewById<Button>(R.id.saveBlog).setOnClickListener{
            if(!validateInputs()){

            }else{
                Log.i("blog", blogContent.text.toString()+ "---"+ blogTitle.text.toString() + "===" + blogAuthor.text.toString() )
            }


        }

        return view
    }

    private  fun validateInputs(): Boolean{


        var error = false
        var errorMessage = ""


        if (blogTitle.text.toString() == ""){
            errorMessage = "Blog Title is Required"
            error = true
        }else if (blogContent.text.toString() == ""){
            errorMessage = "Blog Content is Required"
            error = true
        }else if (blogAuthor.text.toString() == "") {
            errorMessage = "Blog Author Name is Required"
            error = true
        }

        if (error){
            Toast.makeText(this.view?.context, errorMessage, Toast.LENGTH_LONG).show()
//            errorMessageView.text = errorMessage

            return false
        }


        return true
    }



}