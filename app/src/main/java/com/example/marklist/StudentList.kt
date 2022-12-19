package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StudentList : AppCompatActivity() {

    private lateinit var dbref:DatabaseReference
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var studentArrayList: ArrayList<StudentItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val studentName = findViewById<EditText>(R.id.studentName)
        studentRecyclerView = findViewById(R.id.studentRecyclerView)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.setHasFixedSize(false)

        studentArrayList = arrayListOf<StudentItem>()


        val grade = intent.getStringExtra("grade").toString()
        val section = intent.getStringExtra("section").toString()
        getStudentData(grade,section)
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
    private fun getStudentData(classS:String, section:String){
        dbref = FirebaseDatabase.getInstance("https://marklist-e101f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(classS).child(section)
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (studentSnapShot in snapshot.children){
                        val student = studentSnapShot.getValue(StudentItem::class.java)
                        studentArrayList.add(student!!)
                    }
                    studentRecyclerView.adapter = MyAdapter(studentArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}