package com.staffprocessor.dao;

import com.staffprocessor.Factory;
import com.staffprocessor.model.Department;
import com.staffprocessor.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentDaoImpl extends AbstractDao implements DepartmentDao {

    private final String AGE_BOUND_AND_DISTRICT_TEMPLATE;

    public DepartmentDaoImpl(Connection connection) {
        super(connection);

        AGE_BOUND_AND_DISTRICT_TEMPLATE = Factory.getAgeBoundAndDictrict();
    }

    public List<Department> getByAgeBoundAndDistrictName(int from, int to, String districtName, String company) {
        String query = String.format(AGE_BOUND_AND_DISTRICT_TEMPLATE, company);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Department> result = new ArrayList<>();

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, from);
            statement.setInt(2, to);
            statement.setString(3, districtName);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Department d = buildDepartment(resultSet);
                result.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return result;
    }

    private Department buildDepartment(ResultSet resultSet) throws SQLException {
        String departmentName = resultSet.getString(1);
        int employeesCount = resultSet.getInt(2);
        Set<Employee> employees = new HashSet<>();

        for (int i = 0; i < employeesCount; i++) {
            employees.add(new Employee());
        }

        return new Department(departmentName, employees);
    }
}
