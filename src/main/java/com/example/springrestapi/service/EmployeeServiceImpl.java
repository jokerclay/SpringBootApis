package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


//    @Override
//    public List<Employee> getEmployees() {
//        return eRepository.findAll();
//    }

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return  eRepository.findAll(pages).getContent();
    }




    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee  = eRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } throw new RuntimeException("不存在 id 为 "+id+ " 的员工");
    }

    @Override
    public void deleteEmployee(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        // 直接重新保存
        return eRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return eRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeeByNameAndLocation(String name,String location) {
        return eRepository.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) {
        // 为 通过关键词 搜索找 sort
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return eRepository.findByNameContaining(keyword, sort);
    }

    @Override
    public List<Employee> getEmployeesByNameOrLocationService(String name, String location) {
        return eRepository.getEmployeesByNameOrLocation(name, location);
    }

    @Override
    public Integer deleteByEmployeeNameService(String name) {
        return eRepository.deleteEmployeeByName(name);
    }
}