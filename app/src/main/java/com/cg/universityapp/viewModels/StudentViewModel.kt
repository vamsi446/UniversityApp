package com.cg.universityapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cg.universityapp.model.RepositoryVM
import com.cg.universityapp.model.Student
import kotlinx.coroutines.launch

class StudentViewModel(app:Application):AndroidViewModel(app) {
    //reference to repository
    private val repo= RepositoryVM(app)
   // var studentList=mutableListOf<Student>()
    var studentList=MutableLiveData<List<Student>>()
    init {
        getStudents()
    }
    fun addStudent(std: Student){
        viewModelScope.launch {
            repo.addStudent(std)
            getStudents()
        }

    }
    fun updateStudent(std:Student){
        viewModelScope.launch{
            repo.updateStudent(std)
            getStudents()
        }

    }
    fun deleteStudent(std:Student){
        viewModelScope.launch{
            repo.deleteStudent(std)
            getStudents()
        }

    }
    fun deleteAll(){
        viewModelScope.launch{
            repo.deleteAll()
            getStudents()
        }

    }
    fun getStudents() {

        viewModelScope.launch {
            val list=repo.allStudents()
            studentList.postValue(list)
        }

    }


}