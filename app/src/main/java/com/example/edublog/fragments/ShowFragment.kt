package com.example.edublog.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

import androidx.navigation.findNavController
import com.example.edublog.R
import com.example.edublog.databinding.FragmentShowBinding
import com.example.edublog.helpers.DataBaseHelper


class ShowFragment : Fragment() {

    private var blogId = 0
    private lateinit var database : DataBaseHelper

    private var _binding:FragmentShowBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        blogId = arguments?.getInt("blogId") ?: 0

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_show, container, false)

        _binding = FragmentShowBinding.inflate(layoutInflater, container, false)
        val view = binding.root
//        FragmentShowBinding.bind(view)

        database = DataBaseHelper(view.context)

        val blog = database.singleBlog(blogId)

        binding.showBlogTitle.text = blog.title
        binding.showBlogContent.text = blog.content
        binding.showBlogAuthor.text = blog.author
        binding.showBlogDate.text = blog.date

        binding.updateBlogButton.setOnClickListener{
            view.findNavController().navigate(ShowFragmentDirections.actionShowFragmentToUpdateBlogFragment(blogId))

        }


        binding.updateDeleteButton.setOnClickListener {

            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    // Delete selected note from database
                    if (database.deleteBlog(blogId)){
                        view.findNavController().popBackStack()
                    }else{
                        Toast.makeText(view.context, "Some error occured, please try later!", Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}