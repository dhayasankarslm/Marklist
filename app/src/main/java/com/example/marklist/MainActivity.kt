package com.example.marklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signIn = findViewById<Button>(R.id.button)
        val usernamee = findViewById<EditText>(R.id.editTextTextPersonName)
        val passwordd = findViewById<EditText>(R.id.editTextTextPersonName2)
        signIn.setOnClickListener {
            if (usernamee.text.toString() == "20010645" && passwordd.text.toString() == "123456789") {
                Toast.makeText(this, "You're now logged in...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Mainmenu::class.java))
            }
            else{
                Toast.makeText(this, "Wrong username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}