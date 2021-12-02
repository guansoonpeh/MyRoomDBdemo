package com.example.myroomdbdemo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myroomdbdemo.entities.Student

@Dao
interface StudentDAO {

    @Insert
    fun insert(student : Student)

    @Query ("SELECT * FROM Student")
    fun getAll( ) : List<Student>

    @Query ("SELECT * FROM Student where programme = :programme")
    fun getByProgramme(programme:String) : List<Student>

    //...
}