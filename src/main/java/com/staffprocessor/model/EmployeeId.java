package com.staffprocessor.model;

public class EmployeeId {

    private Long staffId;
    private Long departmentId;

    public EmployeeId() {
    }

    public EmployeeId(Long staffId, Long departmentId) {
        this.staffId = staffId;
        this.departmentId = departmentId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
