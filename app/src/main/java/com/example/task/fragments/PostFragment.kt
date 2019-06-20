package com.example.task.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import com.snaku.Adapter.Post_Adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONArray

class PostFragment : Fragment() {

    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


         mView =  inflater!!.inflate(R.layout.activity_main, container, false)


        mView.recyclerview.layoutManager = LinearLayoutManager(activity!!)

        getCategory()

        return mView;
    }

    private fun getCategory() {

        val queue = Volley.newRequestQueue(activity)

        val url = "https://jsonplaceholder.typicode.com/posts"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                val array = JSONArray(response)

                Log.e("jsonarray",array.toString())
                recyclerview.adapter= Post_Adapter(activity!!,array)


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


}