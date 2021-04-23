package com.cg.universityapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],version = 1)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDAO():StudentDAO

    companion object{
        private var instance:StudentDatabase?=null
       /* fun getInstance(context: Context):StudentDatabase{
            if(instance==null)
            instance= buildDatabase(context)
            return instance!!

           // return instance?: buildDatabase(context)
        }*/
        fun getInstance(context: Context)= instance?: buildDatabase(context).also { instance=it }
        private fun buildDatabase( context: Context): StudentDatabase {
            val builder=Room.databaseBuilder(context.applicationContext,
                StudentDatabase::class.java,
                "students.db")
            return builder.build()






        }




    }
}