package com.example.springrestapi.repository;

import com.example.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autowried to Service
 *
 * */
@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//====================
// Paging And Sorting
//====================
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    /**
     * 除了增删改查之外的操作，排序, 分页的操作 使用 JPA 提供的 api,
     * 关键字 findBy+字段名
     * */
    List<Employee> findByName(String name);




    /**
     *  使用 JPA SQL 多个字段
     *  ie:
     *      select * from tbl_employee where `name` = "tim" and `department` = "IT";
     *                          |
     *                          V
     *                  findByNameAndDepartment(String name, String department)
     *
     * */
    List<Employee> findByNameAndLocation(String name, String location);





    /**
     *  使用 JPA SQL like
     *  ie:
     *      select * from tbl_employee where name like "%to%";
     *                          |
     *                          V
     *                  findByNameContaining(String Keyword)
     *                  findByNameLike(String "%" + Keyword + "%")
     *
     * */
    List<Employee> findByNameContaining(String keyword, Sort sort);





}
