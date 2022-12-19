package com.example.marklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList:ArrayList<StudentItem>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.student_item,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.name.text = currentItem.name
        holder.rollNo.text = currentItem.rollNo.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        val name : TextView = itemView.findViewById(R.id.nameOfStudent)
        val rollNo : TextView = itemView.findViewById(R.id.rollNumber)
    }
}