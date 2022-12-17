package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class StudentList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val studentName = findViewById<TextView>(R.id.studentName)
        val grade = intent.getStringExtra("grade").toString()
        val section = intent.getStringExtra("section").toString()

        val mDatabase = FirebaseDatabase.getInstance("https://marklist-e101f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        val studentInstance = Student(studentName.text.toString())
        registerButton.setOnClickListener {
            mDatabase.child(grade).child(section).setValue(studentInstance).addOnCompleteListener{
                if(it.isComplete){
                    Toast.makeText(this,"successfully added",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}