package com.example.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {
    // ===========================
    //  @JsonProperty 使字段有其他名字（alias)
    // ===========================

//    @JsonProperty("full_name")
    private String name;

    // ===========================
    //  @JsonIgnore  使字段不会返回
    //  用于隐藏敏感数据
    // ===========================

//    @JsonIgnore
    private Long age;

    private String location;

    private String email;

    private String department;

}
