package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StudentList : AppCompatActivity() {
    //Variables
    private lateinit var registerButton:Button
    private lateinit var studentName: EditText
    private lateinit var studentNumber: EditText
    private lateinit var mDatabase: DatabaseReference
    private lateinit var grade: String
    private lateinit var section: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        //UI Widgets
        registerButton = findViewById(R.id.registerButton)
        studentName = findViewById(R.id.studentName)
        studentNumber = findViewById(R.id.studentNumber)
        //Input values
        grade = intent.getStringExtra("grade").toString()
        section = intent.getStringExtra("section").toString()

        //Database reference
        mDatabase = FirebaseDatabase.getInstance("https://marklist-e101f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        //ClickListener
        registerButton.setOnClickListener {
            register()
        }
    }
    fun register(){
        val studentNameValue = studentName.text.toString().trim()
        val studentNumberValue = studentNumber.text.toString().trim()
        if(studentNameValue.isEmpty()){
            Toast.makeText(this,"Enter a name",Toast.LENGTH_SHORT).show()
            studentName.requestFocus()
        }
        else if (studentNumberValue.isEmpty()){
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