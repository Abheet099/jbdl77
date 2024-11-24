package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/")
    public void getEmployeeDetails(@RequestParam(required = false, defaultValue = "10") int employeeId) {
        logger.info("Request param received: {}", employeeId);
    }

    @GetMapping("/{empId}")
    public void getEmployeeDetailsByPathVariable(@PathVariable int empId) {
        logger.info("Path variable received: {}", empId);
    }

    @PostMapping(value = {"/requestbody", "/api/emp"})
    public void saveEmployeeDetails(@RequestBody Employee employee) {
        logger.info("Request body received: {}", employee);
    }

    @GetMapping("/details")
    public Employee getEmployeeDetails() {
        Employee employee = new Employee("1", "ram");
        logger.info(employee.toString());
        return employee;
    }

}
