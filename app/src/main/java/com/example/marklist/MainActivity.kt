package com.example.marklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signIn = findViewById<Button>(R.id.button)
        signIn.setOnClickListener {
            startActivity(Intent(this,Mainmenu::class.java))
        }
    }
}