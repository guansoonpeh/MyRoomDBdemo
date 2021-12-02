package com.example.myroomdbdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myroomdbdemo.dao.StudentDAO
import com.example.myroomdbdemo.entities.Student

@Database(entities = [Student::class], version = 1,  exportSchema = false)
abstract class MyDatabase: RoomDatabase() {

    abstract val myStudentDao: StudentDAO

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}