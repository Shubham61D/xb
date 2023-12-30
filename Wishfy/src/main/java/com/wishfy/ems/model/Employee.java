    package com.wishfy.ems.model;

    import lombok.*;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import javax.persistence.*;
    import javax.validation.constraints.NotNull;
    import java.util.Collection;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Slf4j
    @Table(name = "employees")
    @Entity
    public class Employee implements UserDetails {

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




        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return this.employeeEmail;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
