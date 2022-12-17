package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Mainmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)
        val classList = listOf("VI","VII","VIII","IX","X",)
        val list = findViewById<Spinner>(R.id.class_spinner)
        val arrayAdapter = ArrayAdapter(this,R.layout.spinner_list,classList)
        list.adapter = arrayAdapter

    }
}