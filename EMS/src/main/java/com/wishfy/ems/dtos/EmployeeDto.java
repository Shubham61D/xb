package com.wishfy.ems.dtos;



import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    @NotEmpty
    private  String name;
    @Email
    @NotNull
    private String employeeEmail;

    @NotEmpty
    @Size(min = 5,max = 50,message = "Employee Department Name Must Be Minimum 5 Character and Maximum 50 Character")
    private String employeeDepartment;

    @NotNull
    private String joiningDate;


}
