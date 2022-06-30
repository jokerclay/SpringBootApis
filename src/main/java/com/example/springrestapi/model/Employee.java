package com.example.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    // ****************************
    //  如果 类中的数据成员 名 与 数据库中的字段 名 相同， 可省去
    // 如:
    //    @Column(name = "name")
    //    private String name;
    //          |
    //          V
    //    private String name;
    //    一样可以运行
    // ****************************

    //    @JsonProperty("full_name")
    @NotNull(message = "用户名不能为空")            // 通过 java validation 确保 不为 null
    private String name;

    // ===========================
    //  @JsonIgnore  使字段不会返回
    //  用于隐藏敏感数据
    // ===========================

//    @JsonIgnore
    private Long age = 0L;

    private String location;
    @Email(message = "请输入有效邮箱")
    private String email;
    @NotNull(message = "部门不能为空")
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
