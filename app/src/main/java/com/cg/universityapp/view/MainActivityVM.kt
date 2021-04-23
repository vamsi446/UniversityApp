package com.cg.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cg.universityapp.R
import com.cg.universityapp.model.Repository
import com.cg.universityapp.model.Student
import com.cg.universityapp.viewModels.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM : AppCompatActivity() {
    lateinit var model:StudentViewModel

    var checkedStudentList= mutableListOf<Student>()
    lateinit var rView:RecyclerView
    var adapter: StudentAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for no memory leak instantiate model in this way
        val vmProvider=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application))

        model=vmProvider.get(StudentViewModel::class.java)

        //DO NOT do this
        //model=StudentViewModel(application)

        //Log.d("MainActivity","$studentList")
        rView=findViewById(R.id.rView)
        rView.layoutManager=LinearLayoutManager(this)
        //updateList()
        model.studentList.observe(this, Observer {
            val stdList = it
            Log.d("MainActivityVM", "Observer: $stdList")

            adapter = StudentAdapter(stdList,
                checkBoxListener = { std, flag ->
                    if(flag)
                    {
                        checkedStudentList.add(std)
                    }
                    else
                    {
                        checkedStudentList.remove(std)
                    }

                }) { std ->
                val intent = Intent(this, StudentActivity::class.java)
                intent.putExtra("from", "update")
                intent.putExtra("fName", std.firstName)
                intent.putExtra("lName", std.lastName)
                intent.putExtra("marks", std.marks)
                intent.putExtra("id", std.id)
                startActivity(intent)
            }
            rView.adapter = adapter
            /* if(adapter==null)
            {
                adapter=StudentAdapter(stdList){
                        std,flag->
                }
                rView.adapter=adapter
            }
            else
            {
                adapter?.notifyDataSetChanged()
            }*/

        })


    }

    override fun onResume() {
        //updateList()
        model.getStudents()
        adapter?.notifyDataSetChanged()
        super.onResume()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Update Student")
        menu?.add("Delete Student")
        menu?.add("Delete All")
        menu?.add("Refresh")
        menu?.add("About us")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title)
        {
            "Add Student" -> {
                //repository.addStudent(Student("John","Smith",90))
                val intent=Intent(this, StudentActivity::class.java)
                intent.putExtra("from","add")
                startActivity(intent)
            }
            "Update Student" -> {
                val intent=Intent(this, StudentActivity::class.java)
                intent.putExtra("from","update")

                startActivity(intent)
            }
            "Delete Student" -> {

                /* for(i in checkedStudentList)
                {
                    repository.deleteStudent(i)
                }
                stdList.removeAll(checkedStudentList)
                adapter.notifyDataSetChanged()
            }

            "Refresh"->{
                updateList()

            }*/
            }
            "Delete All"->{
                model.deleteAll()
            }
            "Refresh"->{
            }
            "About us"->{
                startActivity(Intent(this,AboutActivity::class.java))
            }

        }
        return super.onOptionsItemSelected(item)
    }
    fun updateList(){
        /*val stdList=model.studentList
        if(adapter==null)
        {
            adapter=StudentAdapter(stdList!!){
                std,flag->
            }
            rView.adapter=adapter
        }
        else
        {
            adapter?.notifyDataSetChanged()
        }

        Log.d("MainActivity","UpdateList : $stdList")
*/
    }

}
