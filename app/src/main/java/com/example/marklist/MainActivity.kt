package com.example.marklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signUp = findViewById<Button>(R.id.button2)
        signUp.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }
    }
}