package com.staffprocessor;

import com.staffprocessor.dao.EmployeeDao;
import com.staffprocessor.dao.EmployeeDaoImpl;
import com.staffprocessor.dao.InitDao;
import com.staffprocessor.dao.InitDaoImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.stream.Stream;

public class Factory {

    private final static String DB_SQL_FILE = "sql/db.sql";
    private final static String INIT_SQL_FILE = "sql/init.sql";

    private final static String SCHEMA_CREATION_QUERY;
    private final static String INIT_SQL_TEMPLATES;

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

        SCHEMA_CREATION_QUERY = getSqlFromFile(DB_SQL_FILE);
        INIT_SQL_TEMPLATES = getSqlFromFile(INIT_SQL_FILE);
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

    public static String getInsertDepartmentTemplate() {
        return INIT_SQL_TEMPLATES.split(";")[0];
    }

    public static String getInsertStaffTemplate() {
        return INIT_SQL_TEMPLATES.split(";")[1];
    }

    public static String getInsertEmployeeTemplate() {
        return INIT_SQL_TEMPLATES.split(";")[2];
    }

    public static String getSchemaCreationQuery() {
        return SCHEMA_CREATION_QUERY;
    }

    private static String getSqlFromFile(String fileLocation) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        try (Stream<String> stream = Files.lines(Paths.get(cl.getResource(fileLocation).toURI()))) {
            return stream.reduce("", (s1, s2) -> s1 + '\n' + s2);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
