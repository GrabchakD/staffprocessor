package com.staffprocessor.driver;

public class Driver {

    public static void main(String[] args) {
        DBInitializerImpl init = new DBInitializerImpl();
        init.initDB();
        System.out.println();
    }
}
