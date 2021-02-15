package com.test.democrud.model

import java.io.Serializable

data class EmployeeModel(
    var Id:Int,
    var firstName:String,
    var lastName:String,
    var phoneNo:String,
    var gender:String,
    var dob:String,
    var designation:String,
    var workExperience:String,
    var bankName:String,
    var ifscCode:String,
    var branchName:String,
    var accountNumber:String,
    var ImageView:ByteArray?=null
):Serializable;