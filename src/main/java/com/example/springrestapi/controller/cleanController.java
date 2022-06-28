package com.example.springrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  URL: http://127.0.0.1:8080/clean
 *  Method: GET
 *  Purpose: 展示 REST api  一种更简洁的写法
 */

//@Controller
@RestController
// @RestController  =  @Controller + @ResponseBody
// 使用  @RestController 就可以不用每次都写 @ResponseBody 了
public class cleanController {
//    @RequestMapping(value = "/clean", method = RequestMethod.GET)
//    @ResponseBody       // 因为接收的是 http request, 所以返回的应该是 http response
    // 使用 get 方法时， 可直接使用 GetMapping
    @GetMapping(value = "clean")
    public String getEmployees() {
        return "展示 REST api  一种更简洁的写法";
    }

}
