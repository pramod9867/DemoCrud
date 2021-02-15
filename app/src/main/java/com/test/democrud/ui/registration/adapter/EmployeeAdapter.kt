package com.test.democrud.ui.registration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.democrud.R
import com.test.democrud.model.EmployeeModel
import com.test.democrud.ui.registration.listners.EmployeeClickListner

class EmployeeAdapter(val userList: List<EmployeeModel>, private val employeeClickListner: EmployeeClickListner,private val layout:Int) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.EmployeeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return EmployeeViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: EmployeeAdapter.EmployeeViewHolder, position: Int) {
        val dataModel=userList.get(position)

        holder.txtName.text=dataModel.firstName+" "+dataModel.lastName;
        holder.textPhoneNumber.text = dataModel.phoneNo;
        holder.txtGender.text=dataModel.gender;
        holder.txtDOB.text=dataModel.dob;

        holder.itemView.setOnClickListener {
            employeeClickListner.onEmployeeClick(dataModel);
        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtName:TextView;
        lateinit var textPhoneNumber:TextView;
        lateinit var txtGender:TextView;
        lateinit var txtDOB:TextView;


        init {
            txtName = itemView.findViewById(R.id.empName) as TextView
            textPhoneNumber  = itemView.findViewById(R.id.empPhone) as TextView
            txtGender  = itemView.findViewById(R.id.gender) as TextView
            txtDOB  = itemView.findViewById(R.id.dob) as TextView



        }


    }
}