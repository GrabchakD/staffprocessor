package com.staffprocessor.processor;

public interface DBInitializer {

    void createSchemas(String company);

    void initData(String company);
}
