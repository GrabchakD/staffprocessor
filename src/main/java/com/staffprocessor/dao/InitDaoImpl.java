package com.staffprocessor.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDaoImpl extends AbstractDao implements InitDao {

    public InitDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void execute(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
