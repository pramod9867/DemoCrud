package com.test.democrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.test.democrud.database.DatabaseHandler
import com.test.democrud.databinding.ActivityMainBinding
import com.test.democrud.model.EmployeeModel
import com.test.democrud.ui.registration.UserDetails
import com.test.democrud.ui.registration.ViewAllData

class MainActivity : AppCompatActivity(),View.OnClickListener {


    lateinit var dbHandler:DatabaseHandler;
    lateinit var mainActivityMainBinding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityMainBinding.fab.setOnClickListener(this);
//        mainActivityMainBinding.fab1.setOnClickListener(this);
        mainActivityMainBinding.viewEmployee.setOnClickListener(this);
        dbHandler= DatabaseHandler(this);


    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            mainActivityMainBinding.fab.id->
//                navigateToForm();
                navigateToForm();
            mainActivityMainBinding.viewEmployee.id->
                navigateToAllEmployee();

        }
    }

    private fun navigateToAllEmployee() {
        val intent = Intent(this, ViewAllData::class.java)
        startActivity(intent);
    }

    fun  getAllData(){
      var test = dbHandler.viewEmployee()

    System.out.println(test);
    }

    private fun navigateToForm() {
        val intent = Intent(this, UserDetails::class.java)
        startActivity(intent);
    }
}