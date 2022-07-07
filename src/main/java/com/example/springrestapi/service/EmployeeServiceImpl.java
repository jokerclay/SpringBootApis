package com.example.springrestapi.service;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service 使该 class 为一个 service class
 * */
@Service
public class EmployeeServiceImpl implements EmployeeService {

/**
 *  Implementations of service
 *
 * */
    @Autowired
    private EmployeeRepository eRepository;

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
}