package com.cg.universityapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cg.universityapp.R
import com.cg.universityapp.databinding.ActivityAboutBinding
import com.cg.universityapp.model.University
import com.cg.universityapp.viewModels.UniversityViewModel

class AboutActivity : AppCompatActivity() {
    lateinit var model:UniversityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_about)


        val vmProvider=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))
        model=vmProvider.get(UniversityViewModel::class.java)
        //model.updateCount()
        val binding=DataBindingUtil.setContentView<ActivityAboutBinding>(this,R.layout.activity_about)

        binding.universityViewModel=model
        binding.lifecycleOwner=this



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Update Count")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //val count=(Math.random()*1000).toInt()
        model.updateCount()
        //Log.d("AboutActivity","$count")
        //model.studentCount.value=count

        return super.onOptionsItemSelected(item)
    }
}