package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service 使该 class 为一个 service class
 * */
@Service
public class EmployeeServiceImpl implements EmployeeService {
//    private static List<Employee> list = new ArrayList<>();
//
//    static {
//        Employee e = new Employee();
//        e.setName("clay");
//        e.setAge(18L);
//        e.setDepartment("IT");
//        e.setEmail("clay@gmali.com");
//        e.setLocation("CN");
//        list.add(e);
//
//        e = new Employee();
//        e.setName("john");
//        e.setAge(38L);
//        e.setDepartment("IT");
//        e.setEmail("john@gmali.com");
//        e.setLocation("AU");
//        list.add(e);
//    }

/**
 *  移除上面写死的数据，通过继承 Repository 对数据库进行 增删改查（CRUD） 操作
 *
 * */
    @Autowired
    private EmployeeRepository eRepository;


    @Override
    public List<Employee> getEmployees() {
        return eRepository.findAll();
    }
}