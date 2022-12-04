package com.example.edublog.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.edublog.R
import com.example.edublog.helpers.DataBaseHelper
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

class ResearchFragment : Fragment() {

     val baseUrl = "https://inshorts.deta.dev/news"
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_research, container, false)

         val spinner = view.findViewById<Spinner>(R.id.news_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
         ArrayAdapter.createFromResource(
             view.context,
             R.array.news_categories,
             android.R.layout.simple_spinner_item
         ).also { adapter ->

             // Specify the layout to use when the list of choices appears
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

             // Apply the adapter to the spinner
             spinner.adapter = adapter
         }


         getArticles(spinner.selectedItem.toString())


         return view
    }

    private fun getArticles(category:String) {
        val client = AsyncHttpClient()
        val params = RequestParams()
        lateinit var jsonData : JSONArray
//        params["limit"] = "5"
//        params["category"] = category

        client["$baseUrl?category=$category", params, object :
            JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON array response with `json.jsonArray`
                Log.i("data", json.toString())

                Log.i("data in apid", json.jsonObject.get("data").toString())
                val data = json?.jsonObject.getJSONArray("data")


            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.e("error", response)
            }
        }]
    }

}