package com.staffprocessor;

import com.staffprocessor.dao.EmployeeDao;
import com.staffprocessor.dao.EmployeeDaoImpl;

import java.sql.Connection;

public class Factory {

    public static Connection getConnection() {
        return null;
    }

    public static EmployeeDao getEmployeeDaoImpl(Connection connection) {
        return new EmployeeDaoImpl(connection);
    }

}
