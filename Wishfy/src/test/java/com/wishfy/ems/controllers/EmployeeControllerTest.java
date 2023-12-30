package com.wishfy.ems.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.services.EmployeeService;
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
}

