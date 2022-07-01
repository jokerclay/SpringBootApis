package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import java.util.List;

public interface EmployeeService {

    // 获取所有员工信息
    List<Employee> getEmployees(int pageNumber, int pageSize);

    // 存储某个员工信息
    Employee saveEmployee(Employee employee);

    // 根据 id 获取某个员工信息
    Employee getSingleEmployee(Long id);

    // 根据 id 删除某个员工信息
    void deleteEmployee(Long id);

    // 更新员工信息
    Employee updateEmployee(Employee employee);

    // 根据 name 获取某个员工信息
    List<Employee> getEmployeeByName(String name);



    // 根据 name 和 department 获取员工信息
    List<Employee> getEmployeeByNameAndLocation(String name, String location);

    // 获取 包含 关键字 员工信息
    List<Employee> getEmployeeByKeyword(String keyword);

}
