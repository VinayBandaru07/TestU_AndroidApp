package com.example.storage_access

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.sql.PreparedStatement

class RecyclerAdapter(private var titles: List<String>, private var images : List<Int>, val onViewClickListener : OnViewClickListener): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.tv_title)
        val itemPicture : ImageView = itemView.findViewById(R.id.iv_image)


        init{
            itemView.setOnClickListener(){
                v:View ->val position  : Int = adapterPosition
                Toast.makeText(itemView.context, "you clicked on item" + position+1, Toast.LENGTH_SHORT ).show()







            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v  = LayoutInflater.from(parent.context).inflate(R.layout.mini_layout, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemPicture.setImageResource(images[position])

        holder.itemView.setOnClickListener(){
            onViewClickListener.onViewClicked((position))
        }







    }

    override fun getItemCount(): Int {
        return titles.size
    }





}