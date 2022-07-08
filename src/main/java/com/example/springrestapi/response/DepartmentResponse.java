package com.example.springrestapi.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentResponse {

    private Long id;

    private String DepartmentName;

    private String employeeName;
}
