package com.staffprocessor;

import com.staffprocessor.dao.EmployeeDao;
import com.staffprocessor.dao.EmployeeDaoImpl;
import com.staffprocessor.dao.InitDao;
import com.staffprocessor.dao.InitDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Factory {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/staffprocessor";
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD
            );
        } catch (Exception e) {
            throw new RuntimeException(String.format("DB Connection is not created: %s", e.getMessage()));
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    public static EmployeeDao getEmployeeDaoImpl(Connection connection) {
        return new EmployeeDaoImpl(connection);
    }

    public static InitDao getInitDao(Connection connection) {
        return new InitDaoImpl(connection);
    }

}
