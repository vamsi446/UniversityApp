package com.cg.universityapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cg.universityapp.model.RepositoryVM
import com.cg.universityapp.model.University
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UniversityViewModel(val app:Application):AndroidViewModel(app)
{
    init {
        updateCount()
    }
    val univ= University("VIT University",
        "Vellore,Tamilnadu",
        "xyz@vit.ac.in")
    var studentCount=MutableLiveData<Int>()

    fun updateCount(){
        val repo= RepositoryVM(app)
        viewModelScope.launch {
            val list=repo.allStudents()
            studentCount.postValue(list.size)
        }
    }

}