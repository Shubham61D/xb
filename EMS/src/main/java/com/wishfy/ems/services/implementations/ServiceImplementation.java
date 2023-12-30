package com.wishfy.ems.services.implementations;

import com.wishfy.ems.dtos.EmployeeDto;
import com.wishfy.ems.exceptions.ResourceNotFoundException;
import com.wishfy.ems.helper.AppConstants;
import com.wishfy.ems.models.Employee;
import com.wishfy.ems.repositories.EmployeeRepository;
import com.wishfy.ems.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceImplementation implements EmployeeService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * @author Shubham Dhokchaule
     * @apiNote THis Method is used for Add  Employee Details
     */
    @Override
    public EmployeeDto addEmployeeU(EmployeeDto employeeDto) {
        log.info("Initiated Dao call for add Employee ");
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        Employee save = this.employeeRepository.save(employee);
        log.info("Completed Dao call for add Employee ");
        return this.modelMapper.map(save, EmployeeDto.class);
    }
    /**
     * @author Shubham Dhokchaule
     * @apiNote THis Method is used for Get All Employee Details
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        log.info("Initiated Dao call for get add Employee ");
        //List<EmployeeDto> employeeDtos =
        log.info("Completed Dao call for add Employee ");
       modelMapper.map(employees,EmployeeDto.class);
        return   employees.stream().map(this::employeeDto).toList();
    }
    /**
     * @author Shubham Dhokchaule
     * @apiNote THis Method is used for Get  Employee By employeeId Details
     */
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.info("Initiated Dao call for get Employee by employeeId:{}", employeeId);
        Employee employee1= this.employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException(AppConstants.NOT_FOUND +  employeeId, 0));
        log.info("Completed Dao call for get Employee by employeeId:{}", employeeId);
        return modelMapper.map(employee1,EmployeeDto.class);

    }
    /**
     * @author Shubham Dhokchaule
     * @apiNote THis Method is used for update Employee Details
     */
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        log.info("Initiated Dao call for update Employee by employeeId:{}", employeeId);
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND +  employeeId, 0));
        employee.setName(employeeDto.getName());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
        employee.setJoiningDate(employeeDto.getJoiningDate());
        log.info("Completed Dao call for update Employee by employeeId:{}", employeeId);
        return  this.modelMapper.map(employee,EmployeeDto.class);
    }
    /**
     * @author Shubham Dhokchaule
     * @apiNote THis Method is used for Remove Employee Details
     */
    @Override
    public void removeEmployee(Long employeeId) {
        log.info("Initiated Dao call for remove Employee");
        Employee employee1 = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND  +  employeeId, 0));
        log.info("Completed Dao call for remove Employee");
        this.employeeRepository.delete(employee1);

    }

    private EmployeeDto employeeDto(Employee employee) {
//        log.info("Initiated Dao call for Employee To Dto ");
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setName(employee.getName());
//        employeeDto.setEmployeeEmail(employee.getEmployeeEmail());
//        employeeDto.setEmployeeDepartment(employee.getEmployeeDepartment());
//        employeeDto.setJoiningDate(employee.getJoiningDate());
//        log.info("Completed Dao call for Employee To DTO ");
        return modelMapper.map(employee,EmployeeDto.class);
    }

    private Employee dtoToEmployee(EmployeeDto employeeDto) {
//        log.info("Initiated Dao call for DTO To Employee");
//        Employee employee = new Employee();
//        employee.setName(employeeDto.getName());
//        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
//        employee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
//        employee.setJoiningDate(employeeDto.getJoiningDate());
//        log.info("Completed Dao call for DTO TO Employee");
        return modelMapper.map(employeeDto,Employee.class);
    }

}
