package com.staffprocessor;

import com.staffprocessor.dao.DepartmentDao;
import com.staffprocessor.dao.DepartmentDaoImpl;
import com.staffprocessor.dao.InitDao;
import com.staffprocessor.dao.InitDaoImpl;
import com.staffprocessor.processor.DBInitializerImpl;
import com.staffprocessor.presentation.ResponseBuilder;
import com.staffprocessor.presentation.ResponseBuilderImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class Factory {

    private final static String DB_SQL_FILE = "sql/db.sql";
    private final static String INIT_SQL_FILE = "sql/init.sql";
    private final static String QUERIES_SQL_FILE = "sql/queries.sql";

    private final static String SCHEMA_CREATION_QUERY;
    private final static String INIT_SQL_TEMPLATES;
    private final static String QUERIES_TEMPLATES;

    private static final String DB_URL = "jdbc:h2:~/staffprocessor";
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
        QUERIES_TEMPLATES = getSqlFromFile(QUERIES_SQL_FILE);
    }


    public static Connection getConnection() {
        return connection;
    }

    public static DepartmentDao getDepatrmentDaoImpl(Connection connection) {
        return new DepartmentDaoImpl(connection);
    }

    public static InitDao getInitDao(Connection connection) {
        return new InitDaoImpl(connection);
    }

    public static DBInitializerImpl getDBInitializerImpl(InitDao initDao) {
        return new DBInitializerImpl(initDao);
    }

    public static ResponseBuilder getResponceBuilderImpl() {
        return new ResponseBuilderImpl();
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

    public static String getAgeBoundAndDictrict() {
        return QUERIES_TEMPLATES.split(";")[0];
    }

    public static String getSchemaCreationQuery() {
        return SCHEMA_CREATION_QUERY;
    }

    private static String getSqlFromFile(String fileLocation) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        InputStream is = cl.getResourceAsStream(fileLocation);
        return convertStreamToString(is);
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
