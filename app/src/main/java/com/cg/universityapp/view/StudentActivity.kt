package com.cg.universityapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cg.universityapp.R
import com.cg.universityapp.model.Repository
import com.cg.universityapp.model.Student
import com.cg.universityapp.viewModels.StudentViewModel
import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity() {
    var from:String?=null

    lateinit var model:StudentViewModel
    var id:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val vmProvider= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application))

        model=vmProvider.get(StudentViewModel::class.java)

        from=intent.getStringExtra("from")
        if(from=="add")
        {
            addB.visibility=View.VISIBLE
        }
        else
        {
            updateB.visibility=View.VISIBLE
            val intent=intent
            val fName=intent.getStringExtra("fName")
            val lName=intent.getStringExtra("lName")
            val marks=intent.getIntExtra("marks",0)
            val id=intent.getIntExtra("id",0)
            firstNameE.setText(fName)
            lastNameE.setText(lName)
            marksE.setText("$marks")

        }
    }

    fun buttonClick(view: View) {
        when(view.id)
        {
            R.id.addB ->{
                val fName=firstNameE.text.toString()
                val lName=lastNameE.text.toString()
                val marks=marksE.text.toString().toInt()
                model.addStudent(Student(fName,lName,marks))
                finish()


            }
            R.id.updateB ->{

                val fName=firstNameE.text.toString()
                val lName=lastNameE.text.toString()
                val marks=marksE.text.toString().toInt()
               model.updateStudent(Student(fName,lName,marks,id))
                finish()

            }
        }
    }
}