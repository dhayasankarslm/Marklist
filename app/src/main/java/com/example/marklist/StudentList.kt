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
        val studentNumber = findViewById<EditText>(R.id.studentNumber)
        val grade = intent.getStringExtra("grade").toString()
        val section = intent.getStringExtra("section").toString()

        val mDatabase = FirebaseDatabase.getInstance("https://marklist-e101f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        registerButton.setOnClickListener {
            val studentNameValue = studentName.text.toString()
            val studentNumberValue = studentNumber.text.toString()
            if(studentNameValue != ""){
                Toast.makeText(this,"Enter a name",Toast.LENGTH_SHORT).show()
                studentName.requestFocus()
            }
            else if (studentNumberValue != ""){
                Toast.makeText(this,"Enter Roll Number",Toast.LENGTH_SHORT).show()
                studentNumber.requestFocus()
            }
            else{
                val studentInstance = Student(studentName.text.toString())
                mDatabase.child(grade).child(section).child(studentNumberValue).setValue(studentInstance).addOnCompleteListener {
                    if (it.isComplete) {
                        Toast.makeText(this, "successfully added", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}