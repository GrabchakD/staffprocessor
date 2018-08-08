package com.staffprocessor.dao;

import com.staffprocessor.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getByAgeRange(int from, int to);

}
