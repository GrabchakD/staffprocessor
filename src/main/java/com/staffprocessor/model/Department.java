package com.staffprocessor.model;

import java.util.HashSet;
import java.util.Set;

public class Department {

    private Long id;
    private String departmentName;
    private String districtName;
    private Set<Employee> employees = new HashSet<>();

    public Department() {
    }

    public Department(String departmentName, Set<Employee> employees) {
        this.departmentName = departmentName;
        this.employees = employees;
    }

    public Department(String departmentName, String districtName) {
        this.departmentName = departmentName;
        this.districtName = districtName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
