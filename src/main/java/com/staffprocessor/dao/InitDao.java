package com.staffprocessor.dao;

import com.staffprocessor.model.Department;
import com.staffprocessor.model.EmployeeId;
import com.staffprocessor.model.Staff;

public interface InitDao {

    void executeSchemaCreation(String company);

    void insertStaff(Staff staff, String company);

    void insertDepartment(Department department, String company);

    void insertEmployee(EmployeeId employee, String company);
}
