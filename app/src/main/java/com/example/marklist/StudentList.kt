package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class StudentList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val studentName = findViewById<EditText>(R.id.studentName)
        val grade = intent.getStringExtra("grade").toString()
        val section = intent.getStringExtra("section").toString()

        val mDatabase = FirebaseDatabase.getInstance("https://marklist-e101f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        registerButton.setOnClickListener {
            val studentInstance = Student(studentName.text.toString())
            mDatabase.child(grade).child(section).child("1").setValue(studentInstance).addOnCompleteListener {
                if (it.isComplete) {
                    Toast.makeText(this, "successfully added", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}