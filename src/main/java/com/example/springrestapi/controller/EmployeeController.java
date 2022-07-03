package com.example.springrestapi.controller;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

/**
 *  Purpose: 安静所有 api 都放到  http://127.0.0.1:8080/api/v1 下
 */
//@RequestMapping("/api/v1")        // Set all the controller under /api/v1, see file `application.properties`

public class EmployeeController {

    /**
     *  Purpose: 注入 EmployeeService
     */
    @Autowired
    private EmployeeService eService;


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
     *  URL: http://127.0.0.1:8080/api/v1/employees
     *  Method: GET
     *  Purpose: 显示员工信息
     *  Return: String
     */
    @GetMapping(value  = "/employees")
//    public String getEmployees() {
//        return "显示员工信息 Controller";
//    }

//    public List<Employee> getEmployees() {
//        return eService.getEmployees();
//    }
//    ======================================
//    不仅要返回 Employee 还要返回 http 状态码OK
//    ======================================

    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

   /**
    * 使用 路径变量 将数据从客户端发送到服务端
    *  URL: http://127.0.0.1:8080/api/v1/employees/69
    *  Method: GET
    *  Purpose: 通过 id 找到员工
    *  Return: String
    * */

   @GetMapping(value  = "/employees/{id}")
//   public String getEmployee(@PathVariable("id") Long id) {
//       return "获取了 id 为 " + id +" 的员工信息";
//   }
//   public Employee getEmployee(@PathVariable("id") Long id) {
//       return eService.getSingleEmployee(id);
//   }
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
//    @DeleteMapping(value = "employees")
//    public String deleteEmployee(@RequestParam("id") Long id) {
//        return "删除 id 为 " + id + " 员工";
//    }
//
//    @DeleteMapping(value = "employees")
//    public void deletoor(@RequestParam("id") Long id) {
//        eService.deleteEmployee(id);
//    }
    @DeleteMapping(value = "employees")
    public ResponseEntity<HttpStatus> deletoor(@RequestParam("id") Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);           // statusCode 204 No Content
    }





    // 多个URL 的参数
//    @DeleteMapping(value = "employees")
//    public String deleteEmployee(@RequestParam Long id, @RequestParam String name) {
//        return "删除 id 为 " + id + " 叫 " + name + " 的员工";
//    }


    /**
     *  URL: http://127.0.0.1:8080/employees/api/v1/?id=69
     *  Method: POST
     *  Purpose: 创建新员工（将 JSON 数据 与 java  object 对应（mapping））
     *  Return: String
     * */

//    @PostMapping(value = "employees")
//    public String saveEmployee(@RequestParam Long id) {
//        return "将员工 "+id+" 信息存储到 数据库中";
//    }

//    @PostMapping(value = "employees")
//    public String saveEmployee(@RequestBody Employee employee) {
//        return "已将员工 "+employee+" 信息存储到 数据库中";
//    }

//    @PostMapping(value = "employees")
//    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
//        return eService.saveEmployee(employee);     // 调用存储 员工 服务
//    }

//    ============================================
//    不仅要返回 Employee 还要返回 http 状态码CREATED
//    ============================================
    @PostMapping(value = "employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);     // 调用存储 员工 服务
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
     *  URL: http://127.0.0.1:8080/api/v1//employees/filterByName?name=john
     *  Method: GET
     *  Purpose: 通过 name 查找
     *
     * */
    @GetMapping(value="/employees/filterByName")
    public ResponseEntity<List<Employee>>  getEmployeeByName(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeeByName(name), HttpStatus.OK);
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/filterByNameAndLocation?name=tim&location=IT
     *  Method: GET
     *  Purpose: 通过 name 和 location 同时查找
     * */
    @GetMapping(value="/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>>  getEmployeeByNameAndDepartment(@RequestParam String name, @RequestParam String location) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name, location), HttpStatus.OK);
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/filterByKeyword?keyword=to
     *  Method: GET
     *  Purpose: 通过 name 的 keyword 查找员工
     * */
    @GetMapping(value="/employees/filterByKeyword")
    public ResponseEntity<List<Employee>>  getEmployeeByKeyword(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(name), HttpStatus.OK);
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/sam/AU
     *  Method: GET
     *  Purpose: 通过 name  或者 location 查找员工
     * */
    @GetMapping(value="/employees/{name}/{location}")
    public ResponseEntity<List<Employee>>  getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameOrLocationService(name, location), HttpStatus.OK);
    }


    /**
     *  URL: http://127.0.0.1:8080/api/v1/employees/sam/AU
     *  Method: GET
     *  Purpose: 通过 name  或者 location 查找员工
     * */
    @DeleteMapping(value="/employees/delete/{name}")
    public ResponseEntity<String>  deleteEmployeeByName(@PathVariable String name) {
        return new ResponseEntity<String>(eService.deleteByEmployeeNameService(name)+ " 被删除了", HttpStatus.OK);
    }

}

