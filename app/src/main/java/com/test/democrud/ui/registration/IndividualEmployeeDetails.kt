package com.test.democrud.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.democrud.R
import com.test.democrud.database.DatabaseHandler
import com.test.democrud.databinding.ActivityIndividualEmpBinding
import com.test.democrud.databinding.ActivityListBinding
import com.test.democrud.databinding.ActivityUserdetailsBinding
import com.test.democrud.model.EmployeeModel
import com.test.democrud.utils.DbBitmapUtility

class IndividualEmployeeDetails: AppCompatActivity(),View.OnClickListener {

    private lateinit var individualBinding: ActivityIndividualEmpBinding;
    lateinit var dbHandler: DatabaseHandler;
    lateinit var data :EmployeeModel;
    var id:Int=1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        individualBinding=DataBindingUtil.setContentView(this,R.layout.activity_individual_emp);
//         id = intent.extras?.get("id") as Int;
        dbHandler= DatabaseHandler(this);
       data =  dbHandler.getEmpById(1)[0];
        individualBinding.empAcc.setText(data.accountNumber);
        individualBinding.empBank.setText(data.bankName);
        individualBinding.empBranch.setText(data.branchName);
        individualBinding.empDesignation.setText(data.designation);
        individualBinding.empIfsc.setText(data.ifscCode);
        individualBinding.empWork.setText(data.workExperience);
        individualBinding.empMobile.setText(data.phoneNo);
        individualBinding.empFirst.setText(data.firstName);
        individualBinding.empLast.setText(data.lastName);
        individualBinding.update.setOnClickListener(this);
        individualBinding.imageView.setImageBitmap(data.ImageView?.let { DbBitmapUtility.getImage(it) });
    }

    fun updateSql(){
        var employeeModel:EmployeeModel =EmployeeModel(
            id,
            individualBinding.empFirst.text.toString(),
            individualBinding.empLast.text.toString(),
            data.phoneNo,
            data.gender,
            data.dob,
            individualBinding.empDesignation.text.toString(),
            individualBinding.empWork.text.toString(),
            individualBinding.empBank.text.toString(),
            individualBinding.empBranch.text.toString(),
            individualBinding.empIfsc.text.toString(),
            individualBinding.empAcc.text.toString()
        )

       val success = dbHandler.updateColumn(employeeModel);
           if(success>0){
               Toast.makeText(this, "Employee updated successfully", Toast.LENGTH_LONG).show();
           }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            individualBinding.update.id->
                updateSql();
        }
    }

}