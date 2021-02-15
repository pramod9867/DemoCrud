package com.test.democrud.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.democrud.R
import com.test.democrud.database.DatabaseHandler
import com.test.democrud.databinding.ActivityListBinding
import com.test.democrud.model.EmployeeModel
import com.test.democrud.ui.registration.adapter.EmployeeAdapter
import com.test.democrud.ui.registration.listners.EmployeeClickListner

class ViewAllData: AppCompatActivity(),EmployeeClickListner {

    private lateinit var viewListBinding:ActivityListBinding;
    lateinit var employeeAdapter: EmployeeAdapter;
    lateinit var dbHandler: DatabaseHandler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewListBinding=DataBindingUtil.setContentView(this,R.layout.activity_list);
        dbHandler= DatabaseHandler(this);
        var test = dbHandler.viewEmployee()
        setAllData(test);

    }


   fun setAllData(listOfEmp:List<EmployeeModel>){
       employeeAdapter = EmployeeAdapter(listOfEmp,this,R.layout.list_employee);
       viewListBinding.recylerView.setLayoutManager(LinearLayoutManager(this))
       viewListBinding.recylerView.isNestedScrollingEnabled=false;
       viewListBinding.recylerView.setAdapter(employeeAdapter)
       viewListBinding.recylerView.visibility= View.VISIBLE;
   }

    override fun onEmployeeClick(employeeModel: EmployeeModel) {
        var intent = Intent(this,IndividualEmployeeDetails::class.java);
        intent.putExtra("id",employeeModel.Id);
        startActivity(intent);
    }
}