package com.staffprocessor.dao;

import com.staffprocessor.Factory;
import com.staffprocessor.model.Department;
import com.staffprocessor.model.EmployeeId;
import com.staffprocessor.model.Staff;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDaoImpl extends AbstractDao implements InitDao {

    private final String INSERT_DEPARTMENT_TEMPLATE;
    private final String INSERT_EMPLOYEE_TEMPLATE;
    private final String INSERT_STAFF_TEMPLATE;
    private final String SCHEMA_CREATION_QUERY;

    public InitDaoImpl(Connection connection) {
        super(connection);

        INSERT_DEPARTMENT_TEMPLATE = Factory.getInsertDepartmentTemplate();
        INSERT_EMPLOYEE_TEMPLATE = Factory.getInsertEmployeeTemplate();
        INSERT_STAFF_TEMPLATE = Factory.getInsertStaffTemplate();
        SCHEMA_CREATION_QUERY = Factory.getSchemaCreationQuery();
    }

    @Override
    public void executeSchemaCreation(String company) {
        String companyASchemaCretion = String.format(SCHEMA_CREATION_QUERY, company);
        execute(companyASchemaCretion);
    }

    @Override
    public void insertStaff(Staff staff, String company) {
        String insertStaffQuery = String.format(
                INSERT_STAFF_TEMPLATE, company, staff.getId(), staff.getName(), staff.getAge()
        );

        execute(insertStaffQuery);
    }

    @Override
    public void insertDepartment(Department dep, String company) {
        String insertDepartmentQuery = String.format(
                INSERT_DEPARTMENT_TEMPLATE, company, dep.getDepartmentName(), dep.getDistrictName()
        );

        execute(insertDepartmentQuery);
    }

    @Override
    public void insertEmployee(EmployeeId emp, String company) {
        String insertEmployeeQuery = String.format(
                INSERT_EMPLOYEE_TEMPLATE, company, emp.getStaffId(), emp.getDepartmentId()
        );

        execute(insertEmployeeQuery);
    }

    private void execute(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
