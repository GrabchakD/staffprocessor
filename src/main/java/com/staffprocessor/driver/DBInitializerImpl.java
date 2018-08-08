package com.staffprocessor.driver;

import com.staffprocessor.Factory;
import com.staffprocessor.dao.InitDao;
import com.staffprocessor.model.Department;
import com.staffprocessor.model.Employee;
import com.staffprocessor.model.EmployeeId;
import com.staffprocessor.model.Staff;

import java.util.Random;

public class DBInitializerImpl implements DBInitializer {

    private final static String COMPANY_A = "COMPANY_A";
    private final static String COMPANY_B = "COMPANY_B";

    private final InitDao initDao = Factory.getInitDao(Factory.getConnection());

    @Override
    public void createSchemas() {
        initDao.executeSchemaCreation(COMPANY_A);
        initDao.executeSchemaCreation(COMPANY_B);
    }

    @Override
    public void initData() {
        initDepartments();
        initStaffAndEmployees();
    }

    private void initDepartments() {
        int departmentCount = 6;
        for (int i = 1; i < departmentCount + 1; i++) {
            String departmentName = String.format("Department_%s", i + 1);
            String districtName = String.format("District_%s", i + 1);

            Department companyADepartment = new Department((long) i, departmentName, districtName);
            Department companyBDepartment = new Department((long) i, departmentName, districtName);

            initDao.insertDepartment(companyADepartment, COMPANY_A);
            initDao.insertDepartment(companyBDepartment, COMPANY_B);
        }
    }

    private void initStaffAndEmployees() {
        Random random = new Random();
        for (int i = 1; i < 500_001; i++) {
            Staff staffForCompanyA = generateRandomStaff(i);
            Staff staffForCompanyB = generateRandomStaff(i);

            initDao.insertStaff(staffForCompanyA, COMPANY_A);
            initDao.insertStaff(staffForCompanyB, COMPANY_B);

            EmployeeId employeeIdForCompanyA = new EmployeeId(staffForCompanyA.getId(), chooseDepartment(random));
            EmployeeId employeeIdForCompanyB = new EmployeeId(staffForCompanyB.getId(), chooseDepartment(random));

            initDao.insertEmployee(employeeIdForCompanyA, COMPANY_A);
            initDao.insertEmployee(employeeIdForCompanyB, COMPANY_B);
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
        int departmentsCount = 6;
        return (long) (random.nextInt((departmentsCount - 1) + 1) + 1);
    }
}
