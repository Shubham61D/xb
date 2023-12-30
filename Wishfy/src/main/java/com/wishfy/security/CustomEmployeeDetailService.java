    package com.wishfy.security;

    import com.wishfy.ems.exceptions.ResourceNotFoundException;
    import com.wishfy.ems.model.Employee;
    import com.wishfy.ems.repositories.EmployeeRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import org.springframework.security.core.userdetails.UserDetailsService;

    import javax.transaction.Transactional;

    @Service

    public class CustomEmployeeDetailService implements UserDetailsService {

        @Autowired(required = true)
        private EmployeeRepository employeeRepository;


        @Override
        @Transactional
        public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
    Employee  employee=this.employeeRepository.findByEmail(employeeEmail).orElseThrow(()->new ResourceNotFoundException("Employee" +employeeEmail,0)) ;
    return employee ;
        }
    }














