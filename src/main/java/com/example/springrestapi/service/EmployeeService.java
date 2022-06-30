package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import java.util.List;

public interface EmployeeService {

    // 获取所有员工信息
    List<Employee> getEmployees();

    // 存储某个员工信息
    Employee saveEmployee(Employee employee);

    // 根据 id 获取某个员工信息
    Employee getSingleEmployee(Long id);
}
