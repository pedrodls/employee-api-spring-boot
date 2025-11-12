package com.isptec.employee_api_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isptec.employee_api_spring_boot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
