package com.wishfy.ems.services;

import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.models.Employee;
import com.wishfy.ems.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class EmployeeServiceTest {
    @Autowired
    ModelMapper mapper;
    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    Employee employee;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .name("Shubham")
                .employeeId(101)
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();
    }

    @Test
    void addEmployeeTest() {
        Mockito.when(employeeRepository.save(Mockito.any()))
                .thenReturn(employee);
        EmployeeDto employee1 = employeeService.addEmployeeU(mapper.map(employee, EmployeeDto.class));

        log.info(employee1.getName());


        Assertions.assertNotNull(employee1);
        Assertions.assertEquals(employee.getName(), employee1.getName());

    }

    @Test
    void getAllEmployeesTest() {
        Employee employee1 = Employee.builder()
                .name("Ajay")
                .employeeId(102)
                .employeeDepartment("Software Tester")
                .employeeEmail("ajay@gmail.com")
                .joiningDate("12-10-2023").build();
        Employee employee2 = Employee.builder()
                .name("Sagar")
                .employeeId(103)
                .employeeDepartment("Data Science Engineer")
                .employeeEmail("sagar@gmail.com")
                .joiningDate("12-07-2023").build();

        List<Employee> employeeList = Arrays.asList(employee, employee1, employee2);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        Assertions.assertEquals(3, allEmployees.size());

    }

    @Test
    void getEmployeeByIdTest() {

        Long employeeId = 101L;
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        EmployeeDto employeeById = employeeService.getEmployeeById(employeeId);
        Assertions.assertNotNull(employeeById);
        // Assertions.assertEquals(employeeById.getEmployeeEmail(),employee.getEmployeeEmail());
    }

    @Test
    void updateEmployeeTest() {

        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("Shubham")
                .employeeEmail("shubham@gmail.com")
                .employeeDepartment("Software Analyst")
                .joiningDate("11-12-2022").build();
        Long employeeId = 101L;
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        EmployeeDto updateEmployee = employeeService.updateEmployee(employeeDto, employeeId);
        log.info(updateEmployee.getName());

    }

    @Test
    void removeEmployeeTest() {
        Long employeeId = 101L;
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        employeeService.removeEmployee(employeeId);

        Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
    }
}