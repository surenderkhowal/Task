package com.example.task

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.task.R
import com.snaku.Adapter.Album_Adapter
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class Listing : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view =  inflater!!.inflate(R.layout.activity_main, container, false)

        view.recyclerview.layoutManager = LinearLayoutManager(activity!!)


        getCategory()

        return view;
    }

    private fun getCategory() {

        val queue = Volley.newRequestQueue(activity)

        val url = "https://jsonplaceholder.typicode.com/albums"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                val array = JSONArray(response)

                Log.e("jsonarray",array.toString())

                sorting(array)

            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            })

        val socketTimeout = 50000
        val policy = DefaultRetryPolicy(
            socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        stringReq.retryPolicy = policy

        queue.add(stringReq)
    }

    fun sorting( jsonArr:JSONArray ){

        val sortedJsonArray = JSONArray()

        val jsonValues = ArrayList<JSONObject>()
        for (i in 0 until jsonArr.length()) {
            jsonValues.add(jsonArr.getJSONObject(i))
        }

        Collections.sort(jsonValues, object : Comparator<JSONObject> {

            private val KEY_NAME = "title"

            override fun compare(a: JSONObject, b: JSONObject): Int {
                var valA = String()
                var valB = String()

                try {
                    valA = a.get(KEY_NAME) as String
                    valB = b.get(KEY_NAME) as String
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                return valA.compareTo(valB)
            }
        })

        for (i in 0 until jsonArr.length()) {
            sortedJsonArray.put(jsonValues[i])
        }

        recyclerview.adapter= Album_Adapter(activity!!,sortedJsonArray)


        Log.e("sorting",sortedJsonArray.toString())
    }

}