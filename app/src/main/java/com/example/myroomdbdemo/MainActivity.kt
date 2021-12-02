package com.example.myroomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myroomdbdemo.dao.StudentDAO
import com.example.myroomdbdemo.database.MyDatabase
import com.example.myroomdbdemo.entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var myDao: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDao = MyDatabase.getInstance(applicationContext).myStudentDao

        val btnSave :Button = findViewById(R.id.btnSave)
        val btnGet :Button = findViewById(R.id.btnGet)

        btnSave.setOnClickListener(){
            val id = findViewById<TextView>(R.id.tfID).text.toString()
            val name = findViewById<TextView>(R.id.tfName).text.toString()
            val programme = findViewById<TextView>(R.id.tfProgramme).text.toString()

            val s = Student(id, name, programme)

            CoroutineScope(IO).launch {
                myDao.insert(s)
            }
        }

        btnGet.setOnClickListener(){
            val tvResult = findViewById<TextView>(R.id.tvResult)

            CoroutineScope(IO).launch {
                val studentList = myDao.getAll()

                var nameList = ""
                for(s:Student in studentList){
                    nameList += s.name + "\n"
                }

                CoroutineScope(Main).launch {
                    tvResult.text = nameList
                }
            }
        }

    }
}