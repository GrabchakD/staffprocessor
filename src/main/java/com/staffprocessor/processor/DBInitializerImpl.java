package com.staffprocessor.processor;

import com.staffprocessor.dao.InitDao;
import com.staffprocessor.model.Department;
import com.staffprocessor.model.EmployeeId;
import com.staffprocessor.model.Staff;

import java.util.Random;

public class DBInitializerImpl implements DBInitializer {

    private final InitDao initDao;

    public DBInitializerImpl(InitDao initDao) {
        this.initDao =  initDao;
    }

    @Override
    public void createSchemas(String company) {
        initDao.executeSchemaCreation(company);
    }

    @Override
    public void initData(String company) {
        initDepartments(company);
        initStaffAndEmployees(company);
    }

    private void initDepartments(String company) {
        int districtCount = 6;
        int departmentsInDistrictCount = 12;

        for(int i = 1; i < districtCount + 1; i++) {
            String districtName = String.format("District_%s", i);

            for (int j = 1; j < departmentsInDistrictCount + 1; j++) {
                String departmentName = String.format("Department_%s", j);
                Department d = new Department(departmentName, districtName);
                initDao.insertDepartment(d, company);
            }
        }
    }

    private void initStaffAndEmployees(String company) {
        Random random = new Random();
        for (int i = 1; i < 500_001; i++) {
            Staff s = generateRandomStaff(i);
            initDao.insertStaff(s, company);

            EmployeeId eId = new EmployeeId(s.getId(), chooseDepartment(random));
            initDao.insertEmployee(eId, company);
        }
    }

    private Staff generateRandomStaff(int id) {
        Random random = new Random();
        String name = choseName(random);
        int age = generateAge(random);
        return new Staff((long) id, name, age);
    }

    private int generateAge(Random random) {
        int minAge = 21;
        int maxAge = 60;
        return random.nextInt((maxAge - minAge) + 1) + minAge;
    }

    private String choseName(Random random) {
        String[] names = {"Tom", "John", "Mike", "Vanessa", "Alice", "Kate"};
        int nameIndex = random.nextInt(names.length);
        return names[nameIndex];
    }

    private long chooseDepartment(Random random) {
        int departmentsCount = 72;
        return (long) (random.nextInt((departmentsCount - 1) + 1) + 1);
    }
}
