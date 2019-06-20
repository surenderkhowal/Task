package com.snaku.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.task.R
import kotlinx.android.synthetic.main.album_adapter.view.*
import org.json.JSONArray
import org.json.JSONException


class Post_Adapter(val context: Context, val album_Array: JSONArray) : RecyclerView.Adapter<Post_Adapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_album_Title = itemView.tv_album_title
        val tv_description = itemView.tv_descripton



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_description.visibility=View.VISIBLE
        try {
            holder.tv_album_Title.setText(album_Array.getJSONObject(position).getString("title").capitalize())
            holder.tv_description.setText(album_Array.getJSONObject(position).getString("body").capitalize())
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        holder.tv_album_Title.setOnClickListener {
            try{
                Toast.makeText(context, album_Array.getJSONObject(position).getString("title"), Toast.LENGTH_SHORT).show()
            }
            catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return album_Array.length()
    }

}