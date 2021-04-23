package com.cg.universityapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Repository(context: Context) {
    private val studentDAO = StudentDatabase.getInstance(context)
        .studentDAO()

    fun addStudent(std: Student) {
        CoroutineScope(Dispatchers.Default).launch{
            studentDAO.insert(std)
        }

    }

    fun updateStudent(std: Student) {
        CoroutineScope(Dispatchers.Default).launch(){
            studentDAO.update(std)
        }


    }

    fun deleteStudent(std: Student){
        CoroutineScope(Dispatchers.Default).launch {
            studentDAO.delete(std)
        }
    }
    fun deleteAll()
    {
        CoroutineScope(Dispatchers.Default).launch {
            studentDAO.deleteAll()
        }

    }
    suspend fun allStudents():List<Student>
    {
        var stdList: MutableList<Student>?=null

        val result=CoroutineScope(Dispatchers.Default).async{
            studentDAO.getStudents()
        }
        stdList=result.await()
        return stdList


    }
}