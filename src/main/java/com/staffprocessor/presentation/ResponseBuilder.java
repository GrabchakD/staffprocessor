package com.staffprocessor.presentation;

import com.staffprocessor.model.Department;

import java.util.List;

public interface ResponseBuilder {

    String buildAgeBoundAndDistrictResponse(List<Department> departmentList);

}
