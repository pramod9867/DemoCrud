package com.test.democrud.ui.registration

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.democrud.R
import com.test.democrud.database.DatabaseHandler
import com.test.democrud.databinding.ActivityBankdetailsBinding
import com.test.democrud.model.EmployeeModel
import com.test.democrud.utils.DbBitmapUtility
import java.util.regex.Matcher
import java.util.regex.Pattern


class BankDetails: AppCompatActivity(),View.OnClickListener,AdapterView.OnItemSelectedListener {
    lateinit var bankDetailsBinding: ActivityBankdetailsBinding;
    lateinit var employeeModel:EmployeeModel;
    private val MY_CAMERA_PERMISSION_CODE = 100;
    private val CAMERA_REQUEST = 1888
    lateinit var dbHandler: DatabaseHandler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bankDetailsBinding= DataBindingUtil.setContentView(this, R.layout.activity_bankdetails);
        employeeModel =intent.extras?.get("EmpDetails") as EmployeeModel;
        dbHandler= DatabaseHandler(this);
        bankDetailsBinding.btnSubmit.setOnClickListener(this);

        val categories: MutableList<String> = ArrayList()
        categories.add("Mumbai")
        categories.add("Goa")
        categories.add("Pune")

        bankDetailsBinding.imageView.setOnClickListener(this);


        // Creating adapter for spinner


        bankDetailsBinding.branch.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        val dataAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bankDetailsBinding.branch.setAdapter(dataAdapter)

    }

    fun doValidation():Boolean{
      if(bankDetailsBinding.bankName.text.isEmpty()){
          Toast.makeText(this, "Please enter bank name", Toast.LENGTH_LONG).show();
          return false;
      }

        if(bankDetailsBinding.accountNumber.text.length!=9){
            Toast.makeText(this, "Please enter account  number", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!validateIfsc(bankDetailsBinding.ifsc.text.toString())){
            Toast.makeText(this, "Please enter valid ifsc number", Toast.LENGTH_LONG).show();
            return  false;

        }

     if(bankDetailsBinding.imageView.drawable==null){
         Toast.makeText(this, "Please select image from camera", Toast.LENGTH_LONG).show();
         return  false;
     }


        return true;

    }

    fun validateIfsc(str:String):Boolean{
        val regex = "^[A-Z]{4}0[A-Z0-9]{6}$";
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(str)
        return m.matches();
    }

    fun addDummyData (){
        val isValid =doValidation();
        if(isValid){
            employeeModel.accountNumber=bankDetailsBinding.accountNumber.text.toString();
            employeeModel.ifscCode=bankDetailsBinding.ifsc.text.toString();
            employeeModel.bankName=bankDetailsBinding.bankName.text.toString();

            var status=  dbHandler.addEmployee(employeeModel);
            if(status>0){
                navigateToView();
            }
        }


    }

    private fun navigateToView() {
        var intent:Intent = Intent(this,ViewAllData::class.java);


        startActivity(intent);
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            bankDetailsBinding.btnSubmit.id->
                addDummyData();
            bankDetailsBinding.imageView.id->
                getImage();
        }
    }

    private fun getImage() {
        if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    MY_CAMERA_PERMISSION_CODE
                )
            }
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    fun  getAllData(){
        var test = dbHandler.viewEmployee();


        System.out.println(test);
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        employeeModel.branchName=p0?.getItemAtPosition(p2).toString();
        Toast.makeText(this, "Selected: " +  employeeModel.branchName, Toast.LENGTH_LONG).show();
        System.out.println("On item selected called"+employeeModel.workExperience);
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CAMERA_REQUEST && resultCode === Activity.RESULT_OK) {
            val photo:Bitmap = data?.getExtras()?.get("data") as Bitmap
            employeeModel.ImageView=DbBitmapUtility.getBytes(photo);
            bankDetailsBinding.imageView.setImageBitmap(photo)
        }
    }

    fun selectImages() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.type = "image/*"
        //        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, your_initial_uri);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        //        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(intent, 1)
    }


}