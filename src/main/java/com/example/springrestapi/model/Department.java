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
@Table(name = "tbl_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "部门名不能为空")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;


    @OneToOne(mappedBy= "department")
    private Employee employee;


}
