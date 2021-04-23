package com.cg.universityapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RepositoryVM(context: Context) {
    private val studentDAO = StudentDatabase.getInstance(context)
        .studentDAO()

    suspend fun addStudent(std: Student)=studentDAO.insert(std)

    suspend fun updateStudent(std: Student)=studentDAO.update(std)

    suspend fun deleteStudent(std: Student)=studentDAO.delete(std)

    suspend fun deleteAll()=studentDAO.deleteAll()

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