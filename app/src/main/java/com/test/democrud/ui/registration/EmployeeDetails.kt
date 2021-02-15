package com.test.democrud.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.test.democrud.R
import com.test.democrud.databinding.ActivityEmpdetailsBinding
import com.test.democrud.model.EmployeeModel


class EmployeeDetails: AppCompatActivity(),View.OnClickListener,AdapterView.OnItemSelectedListener {

    lateinit var empDetailsBinding: ActivityEmpdetailsBinding;
    lateinit var employeeModel:EmployeeModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        empDetailsBinding= DataBindingUtil.setContentView(this, R.layout.activity_empdetails);
        employeeModel =intent.extras?.get("UserDetails") as EmployeeModel;

        empDetailsBinding.btnSubmit.setOnClickListener(this);

//        empDetailsBinding.fab.setOnClickListener(this);


        // Spinner Drop down elements

        // Spinner Drop down elements
        val categories: MutableList<String> = ArrayList()
        categories.add("1")
        categories.add("2")
        categories.add("3")
        categories.add("4")
        categories.add("5")
        categories.add("6")
        categories.add("7")
        categories.add("8")
        categories.add("9")
        categories.add("10")
        categories.add("10+")

        // Creating adapter for spinner


        empDetailsBinding.workExp.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        val dataAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner



        // attaching data adapter to spinner
        empDetailsBinding.workExp.setAdapter(dataAdapter)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            empDetailsBinding.btnSubmit.id->
                addEmp();
        }
    }

    private fun addEmp() {
        var isValid =doValidation();
        if(isValid){
            var intent :Intent = Intent(this,BankDetails::class.java);
            employeeModel.designation=empDetailsBinding.designation.text.toString();

            intent.putExtra("EmpDetails",employeeModel as EmployeeModel);
            startActivity(intent);
        }

    }

    fun doValidation():Boolean{
        if(empDetailsBinding.designation.text.isEmpty()){
            Toast.makeText(this, "Please enter designation", Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        employeeModel.workExperience=p0?.getItemAtPosition(p2).toString();
//        Toast.makeText(this, "Selected: " +  employeeModel.workExperience, Toast.LENGTH_LONG).show();
//        System.out.println("On item selected called"+employeeModel.workExperience);
    }


}