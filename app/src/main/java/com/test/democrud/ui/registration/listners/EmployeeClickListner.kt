package com.test.democrud.ui.registration.listners

import com.test.democrud.model.EmployeeModel


interface EmployeeClickListner {
    fun onEmployeeClick(employeeModel: EmployeeModel);
}