package com.staffprocessor.presentation;

import com.staffprocessor.model.Department;

import java.util.List;

public class ResponseBuilderImpl implements ResponseBuilder {

    @Override
    public String buildAgeBoundAndDistrictResponse(List<Department> departmentList) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader());

        departmentList.forEach(d -> sb
                .append("| ")
                .append(d.getDepartmentName())
                .append(" | ")
                .append(d.getEmployees().size())
                .append("   |")
                .append("\n")
        );

        sb.append(getFooter());
        return sb.toString();
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================").append("\n");
        sb.append("| ").append("Department").append("   | ").append("Employees").append(" |").append("\n");
        sb.append("===========================").append("\n");
        return sb.toString();
    }

    private String getFooter() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================");
        return sb.toString();
    }
}
