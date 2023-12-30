package com.wishfy.ems.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.services.EmployeeService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeController#addEmployee(EmployeeDto)}
     */
    @Test
    void testAddEmployee() throws Exception {
        when(employeeService.addEmployee(Mockito.<EmployeeDto>any()))
                .thenReturn(new EmployeeDto("Name", "jane.doe@example.org", "Employee Department", "2020-03-01"));

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeDepartment("Employee Department");
        employeeDto.setEmployeeEmail("jane.doe@example.org");
        employeeDto.setJoiningDate("2020-03-01");
        employeeDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(employeeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"employeeEmail\":\"jane.doe@example.org\",\"employeeDepartment\":\"Employee Department\","
                                        + "\"joiningDate\":\"2020-03-01\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#addEmployee(EmployeeDto)}
     */
    @Test
    void testAddEmployee2() throws Exception {
        when(employeeService.addEmployee(Mockito.<EmployeeDto>any()))
                .thenReturn(new EmployeeDto("Name", "jane.doe@example.org", "Employee Department", "2020-03-01"));

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeDepartment("?");
        employeeDto.setEmployeeEmail("jane.doe@example.org");
        employeeDto.setJoiningDate("2020-03-01");
        employeeDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(employeeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/employees");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees2() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/employees");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(EmployeeDto, Long)}
     */
    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(Mockito.<EmployeeDto>any(), Mockito.<Long>any()))
                .thenReturn(new EmployeeDto("Name", "jane.doe@example.org", "Employee Department", "2020-03-01"));

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeDepartment("Employee Department");
        employeeDto.setEmployeeEmail("jane.doe@example.org");
        employeeDto.setJoiningDate("2020-03-01");
        employeeDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(employeeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees/{employeeId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"employeeEmail\":\"jane.doe@example.org\",\"employeeDepartment\":\"Employee Department\","
                                        + "\"joiningDate\":\"2020-03-01\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).removeEmployee(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/{employeeId}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() throws Exception {
        doNothing().when(employeeService).removeEmployee(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/{employeeId}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployee(Long)}
     */
    @Test
    void testGetEmployee() throws Exception {
        when(employeeService.getEmployeeById(Mockito.<Long>any()))
                .thenReturn(new EmployeeDto("Name", "jane.doe@example.org", "Employee Department", "2020-03-01"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/{employeeId}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"employeeEmail\":\"jane.doe@example.org\",\"employeeDepartment\":\"Employee Department\","
                                        + "\"joiningDate\":\"2020-03-01\"}"));
    }
}

