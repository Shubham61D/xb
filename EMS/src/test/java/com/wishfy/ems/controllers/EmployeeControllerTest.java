package com.wishfy.ems.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.models.Employee;
import com.wishfy.ems.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private EmployeeService employeeService;

    private Employee employee;


    @BeforeEach
    public void init() {
         employee = Employee.builder()
                .name("Shubham")
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();
    }

    @Test
    void addEmployeeTest() throws Exception {

        Employee employee = Employee.builder()
                .name("Shubham")
                .employeeId(101)
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();


        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String writeValueAsString = objectMapper.writeValueAsString(employee);

        Mockito.when(employeeService.addEmployeeU(Mockito.any())).thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString)
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        // Arrange
        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("Shubham")
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();

        EmployeeDto employeeDto1 = EmployeeDto.builder()
                .name("Shubham")
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();
        List<EmployeeDto> employeeList = Arrays.asList(employeeDto, employeeDto1);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);

        // Act and Assert
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getEmployeeByIdTest() throws Exception {

        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("Shubham")
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();

        Long employeeId = 1L;
       Mockito. when(employeeService.getEmployeeById(employeeId)).thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeEmail").value(employeeDto.getEmployeeEmail()));
    }

    @Test
    void updateEmployeeTest() throws Exception {
        Long employeeId = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("Shubham")
                .employeeDepartment("Software Engineer")
                .employeeEmail("shubham@gmail.com")
                .joiningDate("12-11-2023").build();
        // Assuming updateEmployee returns the updated EmployeeDto
        Mockito.when(employeeService.updateEmployee(Mockito.any(), Mockito.any())).thenReturn(employeeDto);

        ObjectMapper objectMapper=new ObjectMapper();
        String writeValueAsString = objectMapper.writeValueAsString(employee);

       this.mockMvc.perform(MockMvcRequestBuilders.put("/employees/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void deleteEmployeeTest() throws Exception {
        Long employeeId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
