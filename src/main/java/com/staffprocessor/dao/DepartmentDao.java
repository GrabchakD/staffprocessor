package com.staffprocessor.dao;

import com.staffprocessor.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> getByAgeBoundAndDistrictName(int from, int to, String districtName, String company);

}
