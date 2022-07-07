package com.example.springrestapi.controller;

import com.example.springrestapi.model.Department;
import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.DepartmentRepository;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.request.EmployeeRequest;
import com.example.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {
    /**
     *  Purpose: 注入 EmployeeService
     */
    @Autowired
    private EmployeeService eService;

    @Autowired
    private EmployeeRepository eRepo;

    @Autowired
    private DepartmentRepository dRepo;
    /**-----------------------------------
     *
     *  ControllerS
     *
    /*------------------------------------

    /**
     *  URL: http://127.0.0.1:8080/api/v1/details
     *  Method: GET
     *  Purpose: 获取应用信息
     *  Return: String
     */
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping(value = "/details")
    public String getAppDetails() {
        return "软件名: "+ appName +"<br>"+
               "软件版本: " + appVersion + "\n";
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/?pageNumber=0&pageSize=50
     *  Method: GET
     *  Purpose: 显示员工信息
     *  Return: String
     */
    @GetMapping(value  = "/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }


   /**
    * 使用 路径变量 将数据从客户端发送到服务端
    *  URL: http://127.0.0.1:8080/api/v1/employees/1
    *  Method: GET
    *  Purpose: 通过 id 找到员工
    *  Return: String
    * */
   @GetMapping(value  = "/employees/{id}")
   public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
       return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
   }

   /**
    * 使用 query param 将数据从客户端发送到服务端
    *  URL: http://127.0.0.1:8080/api/v1/employees/?id=69
    *       多个参数使用 & 分割
    *       http://127.0.0.1:8080/employees/api/v1/69&username=john
    *  Method: DELETE
    *  Purpose: 通过 key value pair 找到员工
    *  Return: String
    * */
    @DeleteMapping(value = "employees")
    public ResponseEntity<HttpStatus> deletoor(@RequestParam("id") Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);           // statusCode 204 No Content
    }

    /**
     *  URL: http://127.0.0.1:8080/employees/api/v1/?id=69
     *  Method: POST
     *  Purpose: 创建新员工（将 JSON 数据 与 java  object 对应（mapping））
     *  Return: String
     *
     * @return*/

    @PostMapping(value = "/employees")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {

//        Department dept = new Department();
//        dept.setName(eRequest.getDepartment());
//
//        dept = dRepo.save(dept);
//
//        Employee employee = new Employee(eRequest);
//        employee.setDepartment(dept);
//
//        employee = eRepo.save(employee);
//        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

        Employee employee  = new Employee(eRequest);
        employee = eRepo.save(employee);

        for (String s : eRequest.getDepartment()) {
            Department d = new Department();
            d.setName(s);
            d.setEmployee(employee);
            dRepo.save(d);
        }
        return new ResponseEntity<String>("记录成功保存",HttpStatus.CREATED);
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/69
     *  Method: PUT
     *  Purpose: 更新已存储在的员工
     *  Return: Employee
     *  Issues: request method 'put' not supported
     *         see more info:  https://blog.csdn.net/japson_iot/article/details/79660293
     * */
    @PutMapping(value="/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/filter/IT
     *  Method: GET
     *  Purpose: 根据部门获取员工
     *  Return: Employee
     *
     * */
//    @GetMapping(value = "/employees/filter/{name}")
//    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String name) {
////       return new ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name), HttpStatus.OK);
//        return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByDepartmentName(name), HttpStatus.OK);
//    }

}

