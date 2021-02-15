package com.test.democrud.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.test.democrud.model.EmployeeModel
import java.sql.Blob

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EmployeeDatabase"
        private val TABLE_CONTACTS = "EmployeeTable"
        private val KEY_ID = "id"
        private val KEY_FIRST_NAME = "firstName"
        private val KEY_LAST_NAME = "lastName"
        private val KEY_PHONE_NO="phoneNo"
        private val KEY_GENDER="gender"
        private val KEY_DOB="dob"
        private val KEY_DESIGNATION="designation"
        private val KEY_WORK_EXP="workExperience"
        private val KEY_BANK_NAME="bankName"
        private val KEY_IFSC="ifscCode"
        private val KEY_BRANCH_NAME="branchName"
        private val KEY_ACC_NO="KEY_ACC_NO"
        private val KEY_IMAGE="KEY_IMAGE"




    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT," + KEY_PHONE_NO + " TEXT,"+ KEY_GENDER + " TEXT,"+ KEY_DOB + " TEXT,"
                + KEY_DESIGNATION +" TEXT," + KEY_WORK_EXP +" TEXT," + KEY_BANK_NAME +" TEXT," + KEY_IFSC +" TEXT,"
                + KEY_BRANCH_NAME+" TEXT," + KEY_IMAGE+" BLOB,"+ KEY_ACC_NO+" TEXT"+ ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addEmployee(emp: EmployeeModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_FIRST_NAME,emp.firstName);
        contentValues.put(KEY_LAST_NAME,emp.lastName);
        contentValues.put(KEY_PHONE_NO,emp.phoneNo);
        contentValues.put(KEY_GENDER,emp.gender);
        contentValues.put(KEY_DOB,emp.dob);
        contentValues.put(KEY_DESIGNATION,emp.designation);
        contentValues.put(KEY_WORK_EXP,emp.workExperience);
        contentValues.put(KEY_BRANCH_NAME,emp.branchName);
        contentValues.put(KEY_IFSC,emp.ifscCode);
        contentValues.put(KEY_BANK_NAME,emp.bankName);
        contentValues.put(KEY_ACC_NO,emp.accountNumber);
        contentValues.put(KEY_IMAGE,emp.ImageView);
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }



    fun getEmpById(Id:Int):List<EmployeeModel>{
        val empList:ArrayList<EmployeeModel> = ArrayList<EmployeeModel>()

        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS where $KEY_ID=$Id"
        val db = this.readableDatabase

        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var Id:Int;
        var firstName: String;
        var lastName:String;
        var phoneNo:String;
        var gender:String;
        var dob:String;
        var designation:String;
        var workExperience:String;
        var bankName:String;
        var ifscCode:String;
        var branchName:String;
        var accountNumber:String;
        var imageView:ByteArray;

        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                firstName = cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))
                designation = cursor.getString(cursor.getColumnIndex(KEY_DESIGNATION))
                phoneNo = cursor.getString(cursor.getColumnIndex(KEY_PHONE_NO))
                gender = cursor.getString(cursor.getColumnIndex(KEY_GENDER))
                dob = cursor.getString(cursor.getColumnIndex(KEY_DOB))
                workExperience = cursor.getString(cursor.getColumnIndex(KEY_WORK_EXP))
                bankName = cursor.getString(cursor.getColumnIndex(KEY_BANK_NAME))
                ifscCode = cursor.getString(cursor.getColumnIndex(KEY_IFSC))
                branchName = cursor.getString(cursor.getColumnIndex(KEY_BRANCH_NAME))
                accountNumber = cursor.getString(cursor.getColumnIndex(KEY_ACC_NO))
                imageView=cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
                val employeeModel= EmployeeModel(Id,firstName,lastName,phoneNo,gender,dob,designation,workExperience,bankName,ifscCode,branchName,accountNumber,imageView);
                empList.add(employeeModel);

            } while (cursor.moveToNext())
        }


        return empList;
    }

    fun viewEmployee():List<EmployeeModel>{
        val empList:ArrayList<EmployeeModel> = ArrayList<EmployeeModel>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Id:Int;
        var firstName: String;
        var lastName:String;
        var phoneNo:String;
        var gender:String;
        var dob:String;
        var designation:String;
        var workExperience:String;
        var bankName:String;
        var ifscCode:String;
        var branchName:String;
        var accountNumber:String;
        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                firstName = cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))
                designation = cursor.getString(cursor.getColumnIndex(KEY_DESIGNATION))
                phoneNo = cursor.getString(cursor.getColumnIndex(KEY_PHONE_NO))
                gender = cursor.getString(cursor.getColumnIndex(KEY_GENDER))
                dob = cursor.getString(cursor.getColumnIndex(KEY_DOB))
                workExperience = cursor.getString(cursor.getColumnIndex(KEY_WORK_EXP))
                bankName = cursor.getString(cursor.getColumnIndex(KEY_BANK_NAME))
                ifscCode = cursor.getString(cursor.getColumnIndex(KEY_IFSC))
                branchName = cursor.getString(cursor.getColumnIndex(KEY_BRANCH_NAME))
                accountNumber = cursor.getString(cursor.getColumnIndex(KEY_ACC_NO))

                val employeeModel= EmployeeModel(Id,firstName,lastName,phoneNo,gender,dob,designation,workExperience,bankName,ifscCode,branchName,accountNumber);
                empList.add(employeeModel);

            } while (cursor.moveToNext())
        }
        return empList
    }


    fun updateColumn(emp: EmployeeModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_FIRST_NAME,emp.firstName);
        contentValues.put(KEY_LAST_NAME,emp.lastName);
        contentValues.put(KEY_PHONE_NO,emp.phoneNo);
        contentValues.put(KEY_GENDER,emp.gender);
        contentValues.put(KEY_DOB,emp.dob);
        contentValues.put(KEY_DESIGNATION,emp.designation);
        contentValues.put(KEY_WORK_EXP,emp.workExperience);
        contentValues.put(KEY_BRANCH_NAME,emp.branchName);
        contentValues.put(KEY_IFSC,emp.ifscCode);
        contentValues.put(KEY_BANK_NAME,emp.bankName);
        contentValues.put(KEY_ACC_NO,emp.accountNumber);


        val success = db.update(TABLE_CONTACTS, contentValues, "$KEY_ID=${emp.Id}",null);
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }


}

