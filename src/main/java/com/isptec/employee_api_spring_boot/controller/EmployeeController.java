package com.isptec.employee_api_spring_boot.controller;

import org.springframework.web.bind.annotation.*;

import com.isptec.employee_api_spring_boot.entity.Employee;
import com.isptec.employee_api_spring_boot.exceptions.EmployeeNotFoundException;
import com.isptec.employee_api_spring_boot.repository.EmployeeRepository;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // GET /employees
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    // POST /employees
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // GET /employees/{id}
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // PUT /employees/{id}
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    // DELETE /employees/{id}
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
