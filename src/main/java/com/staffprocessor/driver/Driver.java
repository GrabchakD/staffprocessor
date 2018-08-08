package com.staffprocessor.driver;


import com.staffprocessor.Factory;
import com.staffprocessor.dao.DepartmentDao;
import com.staffprocessor.model.Department;
import com.staffprocessor.model.Employee;
import com.staffprocessor.presentation.ResponseBuilder;
import com.staffprocessor.processor.DBInitializerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {

    private final static String COMPANY_A = "COMPANY_A";
    private final static String COMPANY_B = "COMPANY_B";

    public static void main(String[] args) {
        DBInitializerImpl init = Factory.getDBInitializerImpl(Factory.getInitDao(Factory.getConnection()));
        DepartmentDao departmentDao = Factory.getDepatrmentDaoImpl(Factory.getConnection());
        ResponseBuilder builder = Factory.getResponceBuilderImpl();

        init.createSchemas(COMPANY_A);
        init.createSchemas(COMPANY_B);

        init.initData(COMPANY_A);
        init.initData(COMPANY_B);

        int employeesAgeFrom = Integer.parseInt(args[0]);
        int employeesAgeTo = Integer.parseInt(args[1]);
        String departmentName = args[2];

        List<Department> departmentsFromCompanyA =
                departmentDao.getByAgeBoundAndDistrictName(employeesAgeFrom, employeesAgeTo, departmentName, COMPANY_A);

        List<Department> departmentsFromCompanyB =
                departmentDao.getByAgeBoundAndDistrictName(employeesAgeFrom, employeesAgeTo, departmentName, COMPANY_B);

        List<Department> result = mergeResults(departmentsFromCompanyA, departmentsFromCompanyB);

        String response = builder.buildAgeBoundAndDistrictResponse(result);

        System.out.println(response);
    }

    private static List<Department> mergeResults(List<Department> departmentsFromCompanyA, List<Department> departmentsFromCompanyB) {
        List<Department> result = new ArrayList<>(departmentsFromCompanyA);

        for(Department dFromA : result) {
            for (Department dFromB : departmentsFromCompanyB) {
                if (dFromA.getDepartmentName().equals(dFromB.getDepartmentName())) {
                    Set<Employee> employees = new HashSet<>(dFromA.getEmployees());
                    employees.addAll(dFromB.getEmployees());
                    dFromA.setEmployees(employees);
                }
            }
        }
        return result;
    }
}
