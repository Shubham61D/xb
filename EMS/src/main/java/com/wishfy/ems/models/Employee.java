package com.wishfy.ems.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "employees")
@Entity
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotNull
    private String name;
    @Column(name = "employee_email",unique = true)
    private String employeeEmail;

    @Column(name = "employee_department")
    private String employeeDepartment;

    @Column(name = "employee_joining_date")
    private String joiningDate;

}
