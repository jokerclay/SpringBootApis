package com.example.springrestapi.repository;

import com.example.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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



    /**
     * 自定义 SQL 方法
     *
     * */
    @Query("From Employee Where name = :name OR location = :location")
//    @Query("From 表名 Where 字段名  = :变量名 OR location = :location")

    List<Employee>  getEmployeesByNameOrLocation  (String name, String location);
//    List<Employee>  getEmployeesByNameOrLocation (@Param("name") String abc, @param("location")String cde);

    @Transactional
    @Modifying  // 自定义的 SQL 更改数据库时要加上 @Modifying 注解
    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteEmployeeByName(String name);

}