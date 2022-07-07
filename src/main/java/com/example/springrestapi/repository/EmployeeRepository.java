package com.example.springrestapi.repository;

import com.example.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String name);

    @Query("FROM Employee WHERE department.name =:name")
    List<Employee> getEmployeesByDepartmentName(String name);
}