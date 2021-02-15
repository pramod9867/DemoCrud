package com.test.democrud.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.democrud.R
import com.test.democrud.databinding.ActivityUserdetailsBinding
import com.test.democrud.model.EmployeeModel
import java.io.Serializable


class UserDetails : AppCompatActivity(),View.OnClickListener {

    lateinit var userDetailsBinding:ActivityUserdetailsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDetailsBinding=DataBindingUtil.setContentView(this,R.layout.activity_userdetails);

        userDetailsBinding.btnSubmit.setOnClickListener(this);

      ;
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            userDetailsBinding.btnSubmit.id->

                submitData();
        }
    }

    fun getGender():String{
        var id =userDetailsBinding.gender.checkedRadioButtonId;

        if(id==-1){
            Toast.makeText(this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{

            if(userDetailsBinding.radioMale.isChecked){
                return "Male";
            }
            if(userDetailsBinding.radioFemale.isChecked){
                return  "Female";
            }
        }

        return  "";

    }

    fun doValidation():Boolean{
        if(userDetailsBinding.firstName.text.isEmpty()){
            Toast.makeText(this, "Please enter first name", Toast.LENGTH_LONG).show();
            return false;
        }
        if(userDetailsBinding.lastName.text.isEmpty()){
            Toast.makeText(this, "Please enter last name", Toast.LENGTH_LONG).show();
            return false;
        }
        if(userDetailsBinding.phoneNumber.text.length<10||userDetailsBinding.phoneNumber.text.length>10){
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_LONG).show();
            return false;
        }

        var id =userDetailsBinding.gender.checkedRadioButtonId;
        if(id==-1){
            Toast.makeText(this, "Please select gender", Toast.LENGTH_LONG).show();
            return false;
        }
        if(userDetailsBinding.dob.text.isEmpty()){
            Toast.makeText(this, "Please enter dob", Toast.LENGTH_LONG).show();
            return  false;
        }

        return  true;
    }

    private fun submitData() {
        val isValid =doValidation();
        if(isValid){
            val temp: ByteArray? = null
            var intent:Intent = Intent(this,EmployeeDetails::class.java);
            var employeeModel:EmployeeModel =EmployeeModel(0,userDetailsBinding.firstName.text.toString(),userDetailsBinding.lastName.text.toString(),userDetailsBinding.phoneNumber.text.toString(),getGender(),userDetailsBinding.dob.text.toString(),"","","","","","");
            intent.putExtra("UserDetails",employeeModel as Serializable);
            startActivity(intent);
        }
    }


}