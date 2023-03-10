package com.example.marklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class Mainmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        val enterClass = findViewById<Button>(R.id.button3)

        val classList = listOf("VI","VII","VIII","IX","X",)
        val list = findViewById<Spinner>(R.id.class_spinner)
        val arrayAdapter = ArrayAdapter(this,R.layout.spinner_list,classList)
        list.adapter = arrayAdapter

        val sectionList = listOf("A","B","C")
        val list2 = findViewById<Spinner>(R.id.section_spinner)
        val arrayAdapter1 = ArrayAdapter(this,R.layout.spinner_list,sectionList)
        list2.adapter = arrayAdapter1

        var grade:String = "VI"
        var section = "A"

        enterClass.setOnClickListener {
            grade = list.selectedItem.toString()
            section = list2.selectedItem.toString()
            val intent = Intent(this,StudentList::class.java)
            intent.putExtra("grade",grade)
            intent.putExtra("section",section)
            startActivity(intent)
        }
    }
}