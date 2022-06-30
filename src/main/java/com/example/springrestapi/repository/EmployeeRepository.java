package com.example.springrestapi.repository;

import com.example.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Autowried to Service
 * */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
