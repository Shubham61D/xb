package com.wishfy.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    @Column(name = "employee_email",unique = true)
    private String employeeEmail;

    @Column(name = "employee_department")
    private String employeeDepartment;

    @Column(name = "employee_joining_date")
    private String joiningDate;
}
