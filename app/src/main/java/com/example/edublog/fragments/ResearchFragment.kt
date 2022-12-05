package com.example.edublog.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.edublog.R
import com.example.edublog.adapters.ArticleAdapter
import com.example.edublog.databinding.FragmentResearchBinding
import com.example.edublog.models.ArticleModel
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

class ResearchFragment : Fragment() {

    private var _binding:FragmentResearchBinding? = null
    private val binding get() = _binding!!
    val baseUrl = "https://inshorts.deta.dev/news" // api to get latest news articles
    var articles = ArrayList<ArticleModel>()



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResearchBinding.inflate(layoutInflater, container, false)

//        val view = inflater.inflate(R.layout.fragment_research, container, false)
        val view = binding.root

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


        val recyclerView = binding.newsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val articleAdapter = ArticleAdapter(view.context, articles)
        recyclerView.adapter = articleAdapter

        getArticles(spinner.selectedItem.toString(), articleAdapter)

        binding.newsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view != null) {
                    getArticles(binding.newsSpinner.selectedItem.toString(), articleAdapter)
//                    recyclerView.adapter?.notify()
                    articleAdapter.notifyDataSetChanged();
                }
            }

        }

        return view
    }


    private fun getArticles(category:String, adapter: ArticleAdapter) {

        val client = AsyncHttpClient()
        val params = RequestParams()
        articles = ArrayList<ArticleModel>()
//        params["limit"] = "5"
//        params["category"] = category

        client["$baseUrl?category=$category", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON array response with `json.jsonArray`
                Log.i("data in apid", json.jsonObject.get("data").toString())
                val data = json?.jsonObject.get("data") as JSONArray

                for (i in 0 until data.length()){
                    val jsonObject = data.getJSONObject(i) as JSONObject
                    articles.add(ArticleModel(author = jsonObject.getString("author"), content = jsonObject.getString("content"), date = jsonObject.getString("date"), imageUrl = jsonObject.getString("imageUrl"), readMoreUrl = jsonObject.getString("readMoreUrl"), time = jsonObject.getString("time"), title = jsonObject.getString("title"), url = jsonObject.getString("url")
                    ))
                }
                val articleAdapter =  ArticleAdapter(view?.context as Context, articles)
                binding.newsRecyclerView.adapter = articleAdapter
                if (adapter!= null){
                    articleAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String, throwable: Throwable?) {
                Log.e("error", response)
            }
        }]

    }

}