package com.wishfy.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

  @Email
  @NotNull
    private String employeeEmail;

    @NotEmpty
    @Size(min = 5,max = 50,message = "Employee Department Name Must Be Minimum 5 Character and Maximum 50 Character")
    private String employeeDepartment;

  @NotNull(message = "Date cannot be null")
    private String joiningDate;
}
