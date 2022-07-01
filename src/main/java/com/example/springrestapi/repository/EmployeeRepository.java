package com.example.springrestapi.repository;

import com.example.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autowried to Service
 *
 * */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * 除了增删改查之外的操作，排序, 分页的操作 使用 JPA 提供的 api,
     * 关键字 findBy+字段名
     * */
    List<Employee> findByName(String name);

}
