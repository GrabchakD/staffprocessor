package com.staffprocessor.dao;

import com.staffprocessor.model.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

    public EmployeeDaoImpl(Connection connection) {
        super(connection);
    }

    public List<Employee> getByAgeRange(int from, int to) {
        return null;
    }
}
