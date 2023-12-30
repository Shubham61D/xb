package com.wishfy.ems.repositories;

import com.wishfy.ems.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends  JpaRepository<Employee,Long> {

        Optional<Employee> findByEmployeeEmail(String employeeEmail);
    }
