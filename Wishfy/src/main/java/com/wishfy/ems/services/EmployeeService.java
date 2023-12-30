package com.wishfy.services;

import com.wishfy.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    //Add New  Employee
    EmployeeDto addEmployee(EmployeeDto employeeDto);


    //Get All Employee
    List<EmployeeDto> getAllEmployees();

    //Get Employee by ID
    EmployeeDto getEmployeeById(Long employeeId);

//Update Employee

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId);

    //remove Employee
    void removeEmployee(Long employeeId);


}
