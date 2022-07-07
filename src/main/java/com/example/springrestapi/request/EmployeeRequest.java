package com.example.springrestapi.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeRequest {
    private String name;
//    private String department;
    private List<String> department;

}
