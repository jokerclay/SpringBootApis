package com.example.springrestapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String name;
}
