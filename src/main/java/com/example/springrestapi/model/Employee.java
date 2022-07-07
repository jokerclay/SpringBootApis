package com.example.springrestapi.model;

import com.example.springrestapi.request.EmployeeRequest;
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

    /**
     * 将 department 与  employee 形成一对一对应关系
     * */
//    @JoinColumn(name = "department_id", nullable = false)
//    @OneToOne
//    private Department department;

    public Employee(EmployeeRequest req) {
        this.name = req.getName();

    }

    public Employee() {

    }
}
