package com.example.marklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val StdList: ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int){
        val currentStudent = StdList[position]
        holder.studentName.text = currentStudent.name
        holder.studentRoll.text = currentStudent.rollNo.toString()
    }

    override fun getItemCount(): Int{
        return StdList.size
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val studentName : TextView = itemView.findViewById(R.id.studentNameView)
        val studentRoll : TextView = itemView.findViewById(R.id.studentRollNumberView)
    }
}