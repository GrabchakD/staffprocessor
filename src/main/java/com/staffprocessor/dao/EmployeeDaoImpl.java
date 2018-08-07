package com.staffprocessor.dao;

import com.staffprocessor.model.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }


    public List<Employee> getByAgeRange(int from, int to) {
        return null;
    }
}
