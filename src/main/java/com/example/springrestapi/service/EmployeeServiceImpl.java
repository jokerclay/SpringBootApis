package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Service 使该 class 为一个 service class
 * */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static List<Employee> list = new ArrayList<>();

    static {
        Employee e = new Employee();
        e.setName("clay");
        e.setAge(18L);
        e.setDepartment("IT");
        e.setEmail("clay@gmali.com");
        e.setLocation("CN");
        list.add(e);

        e = new Employee();
        e.setName("john");
        e.setAge(38L);
        e.setDepartment("IT");
        e.setEmail("john@gmali.com");
        e.setLocation("AU");
        list.add(e);
    }

    @Override
    public List<Employee> getEmployees() {
        return list;
    }
}