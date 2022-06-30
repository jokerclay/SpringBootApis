package com.example.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
//@Entity(name = "tbl_employee")
@Entity
@Table(name = "tbl_employee")
public class Employee {
    // ===========================
    //  @JsonProperty 使字段有其他名字（alias)
    // ===========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 对应 数据库中的 自增
    @Column(name = "id", nullable = false)
    private Long id;

    //    @JsonProperty("full_name")
    @Column(name = "name")
    private String name;

    // ===========================
    //  @JsonIgnore  使字段不会返回
    //  用于隐藏敏感数据
    // ===========================

//    @JsonIgnore
    @Column(name = "age")
    private Long age;

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;


    /**
     * 时间戳
     * */
    @CreationTimestamp()
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;



}
