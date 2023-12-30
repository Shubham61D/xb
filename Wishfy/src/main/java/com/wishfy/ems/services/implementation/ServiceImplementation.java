package com.wishfy.ems.services.implementation;

import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.helper.AppConstants;
import com.wishfy.ems.repositories.EmployeeRepository;
import com.wishfy.ems.services.EmployeeService;
import com.wishfy.ems.exceptions.ResourceNotFoundException;
import com.wishfy.ems.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class ServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        log.info("Initiated Dao call for add Employee ");
        Employee employee = this.dtoToEmployee(employeeDto);
        Employee save = this.employeeRepository.save(employee);
        log.info("Completed Dao call for add Employee ");
        return this.employeeDto(save);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        log.info("Initiated Dao call for get add Employee ");
        //List<EmployeeDto> employeeDtos =
        log.info("Completed Dao call for add Employee ");
        return  employees.stream().map(this::employeeDto).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.info("Initiated Dao call for get Employee by employeeId:{}", employeeId);
        Employee employee1= this.employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException(AppConstants.NOT_FOUND +  employeeId, 0));
        log.info("Completed Dao call for get Employee by employeeId:{}", employeeId);
        return employeeDto(employee1);

    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        log.info("Initiated Dao call for update Employee by employeeId:{}", employeeId);
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND +  employeeId, 0));
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
        employee.setJoiningDate(employeeDto.getJoiningDate());
        Employee updatedEmployee = this.employeeRepository.save(employee);
        log.info("Completed Dao call for update Employee by employeeId:{}", employeeId);
        return  this.employeeDto(updatedEmployee);
    }

    @Override
    public void removeEmployee(Long employeeId) {
        log.info("Initiated Dao call for remove Employee");
        Employee employee1 = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND  +  employeeId, 0));
        log.info("Completed Dao call for remove Employee");
        this.employeeRepository.delete(employee1);

    }

    private EmployeeDto employeeDto(Employee employee) {
        log.info("Initiated Dao call for Employee To Dto ");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeEmail(employee.getEmployeeEmail());
        employeeDto.setEmployeeDepartment(employee.getEmployeeDepartment());
        employeeDto.setJoiningDate(employee.getJoiningDate());
        log.info("Completed Dao call for Employee To DTO ");
        return employeeDto;
    }

    private Employee dtoToEmployee(EmployeeDto employeeDto) {
        log.info("Initiated Dao call for DTO To Employee");
        Employee employee = new Employee();
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
        employee.setJoiningDate(employeeDto.getJoiningDate());
        log.info("Completed Dao call for DTO TO Employee");
        return employee;
    }
}