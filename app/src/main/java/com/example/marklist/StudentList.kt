package com.example.marklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StudentList : AppCompatActivity() {
    //Variables
    private lateinit var registerButton:Button
    private lateinit var studentName: EditText
    private lateinit var studentNumber: EditText
    private lateinit var mDatabase: DatabaseReference
    private lateinit var grade: String
    private lateinit var section: String
    private lateinit var recycler: RecyclerView
    private lateinit var stdList: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        //UI Widgets
        registerButton = findViewById(R.id.registerButton)
        studentName = findViewById(R.id.studentName)
        studentNumber = findViewById(R.id.studentNumber)
        recycler = findViewById(R.id.studentList)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        stdList = arrayListOf<Student>()
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
            getStudent()
            Toast.makeText(this,"Enter a name",Toast.LENGTH_SHORT).show()
            studentName.requestFocus()
        }
        else if (studentNumberValue.isEmpty()){
            Toast.makeText(this,"Enter Roll Number",Toast.LENGTH_SHORT).show()
            studentNumber.requestFocus()
        }
        else{
            val studentInstance = Student(studentName.text.toString(),studentNumberValue.toInt())
            mDatabase.child(grade).child(section).child(studentNumberValue).setValue(studentInstance).addOnCompleteListener {
                if (it.isComplete) {
                    Toast.makeText(this, "successfully added", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getStudent(){
        mDatabase.child(grade).child(section).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                stdList.clear()
                if(snapshot.exists()){
                    for(stdSnp in snapshot.children){
                        val StdName = stdSnp.getValue(Student::class.java)
                        stdList.add(StdName!!)
                    }
                    val stdAdapter = StudentAdapter(stdList)
                    recycler.adapter = stdAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@StudentList ,"error",Toast.LENGTH_SHORT).show()
            }

        })
    }
}