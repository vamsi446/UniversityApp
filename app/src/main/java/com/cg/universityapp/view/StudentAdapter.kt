package com.cg.universityapp.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cg.universityapp.R
import com.cg.universityapp.model.Student
class StudentAdapter(val stdList:List<Student>,val checkBoxListener:(Student,Boolean)->Unit,val itemListener:(Student)->Unit):RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var fNameT:TextView
        var lNameT:TextView
        var marksT: TextView
        var checkBox:CheckBox

        init {
            fNameT=view.findViewById(R.id.fNameT)
            lNameT=view.findViewById(R.id.lNameT)
            marksT=view.findViewById(R.id.marksT)
            checkBox=view.findViewById(R.id.checkBox)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val inflatedView=inflater.inflate(R.layout.student_item,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val std=stdList[position]
        holder.fNameT.setText(std.firstName)
        holder.lNameT.setText(std.lastName)
        holder.marksT.setText("${std.marks}")
        holder.checkBox.isChecked=false
        holder.checkBox.setOnClickListener{
            if(it is CheckBox && it.isChecked)
            {
                checkBoxListener(std,true)
            }
            else
            {
                checkBoxListener(std,false)
            }

        }
        holder.itemView.setOnClickListener{
            itemListener(std)
        }

    }

    override fun getItemCount(): Int {
        return stdList.size
    }
}