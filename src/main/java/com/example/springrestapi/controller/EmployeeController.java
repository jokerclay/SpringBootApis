package com.example.springrestapi.controller;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

/**
 *  Purpose: 安静所有 api 都放到  http://127.0.0.1:8080/api/v1 下
 */
@RequestMapping("/api/v1")
public class EmployeeController {


    /**
     *  Purpose: 注入 EmployeeService
     *  Return: List
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

    public List<Employee> getEmployees() {
        return eService.getEmployees();
    }

   /**
    * 使用 路径变量 将数据从客户端发送到服务端
    *  URL: http://127.0.0.1:8080/api/v1/employees/69
    *  Method: GET
    *  Purpose: 通过 id 找到员工
    *  Return: String
    * */

   @GetMapping(value  = "/employees/{id}")
   public String getEmployee(@PathVariable("id") Long id) {
       return "获取了 id 为 " + id +" 的员工信息";
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
    public String deleteEmployee(@RequestParam("id") Long id) {
        return "删除 id 为 " + id + " 员工";
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

    @PostMapping(value = "employees")
    public String saveEmployee(@RequestBody Employee employee) {
        return "已将员工 "+employee+" 信息存储到 数据库中";
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
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        System.out.println("更新了 员工 " +id);
        return employee;
    }

}

