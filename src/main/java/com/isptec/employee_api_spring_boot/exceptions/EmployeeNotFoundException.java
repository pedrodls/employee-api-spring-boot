package com.isptec.employee_api_spring_boot.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee id not found : " + id);
    }
}
