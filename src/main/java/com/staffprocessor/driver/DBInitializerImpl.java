package com.staffprocessor.driver;

import com.staffprocessor.Factory;
import com.staffprocessor.dao.InitDao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DBInitializerImpl implements DBInitializer {

    private final static String SQL_FILE = "sql/db.sql";
    private final static String COMPANY_A = "COMPANY_A";
    private final static String COMPANY_B = "COMPANY_B";

    private final InitDao initDao = Factory.getInitDao(Factory.getConnection());

    @Override
    public void initDB() {
        String sqlFromFile = getSqlFromFile();
        String companyASchemaSql = String.format(sqlFromFile, COMPANY_A);
        String companyBSchemaSql = String.format(sqlFromFile, COMPANY_B);
        initDao.execute(companyASchemaSql);
        initDao.execute(companyBSchemaSql);
    }

    @Override
    public void initData() {

    }

    private String getSqlFromFile() {
        ClassLoader cl = getClass().getClassLoader();

        try (Stream<String> stream = Files.lines(Paths.get(cl.getResource(SQL_FILE).toURI()))) {
            return stream.reduce("", (s1, s2) -> s1 + '\n' + s2);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
