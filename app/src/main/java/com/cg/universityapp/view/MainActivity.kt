package com.cg.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cg.universityapp.R
import com.cg.universityapp.model.Repository
import com.cg.universityapp.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var repository:Repository
    var checkedStudentList= mutableListOf<Student>()
    var stdList= mutableListOf<Student>(Student("","",0))
    lateinit var rView:RecyclerView
    lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository= Repository(this)
        rView=findViewById(R.id.rView)
        updateList()
        //Log.d("MainActivity","$studentList")
        rView.layoutManager=LinearLayoutManager(this)
        adapter= StudentAdapter(stdList,{
                std,flag->
            if(flag)
            {
                checkedStudentList.add(std)

            }
            else
            {
                checkedStudentList.remove(std)
            }
        }){
                std->
        }

        rView.adapter=adapter




    }

    override fun onResume() {
        updateList()
        super.onResume()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Update Student")
        menu?.add("Delete Student")
        menu?.add("Delete All")
        menu?.add("Refresh")
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
                for(i in checkedStudentList)
                {
                    repository.deleteStudent(i)
                }
                stdList.removeAll(checkedStudentList)
                adapter.notifyDataSetChanged()
            }
            "Delete All" -> {
                repository.deleteAll()
            }
            "Refresh"->{
                updateList()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    fun updateList(){
        CoroutineScope(Dispatchers.Default).launch {
            stdList= repository.allStudents() as MutableList<Student>
            //adapter.notifyDatasetChanged()
            CoroutineScope(Dispatchers.Main).launch {  adapter.notifyDataSetChanged()
                adapter= StudentAdapter(stdList,{
                    std,flag->
                    if(flag)
                    {
                        checkedStudentList.add(std)

                    }
                    else
                    {
                        checkedStudentList.remove(std)
                    }
                }){
                    std->
                }
                rView.adapter=adapter }

            Log.d("MainActivity","UpdateList : $stdList")

        }
    }
}
