package com.example.myroomdbdemo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student(

    @PrimaryKey
    var studentId:String = "",

    @ColumnInfo(name = "name")
    var name:String = "",

    @ColumnInfo(name = "programme")
    var programme:String = ""

)
