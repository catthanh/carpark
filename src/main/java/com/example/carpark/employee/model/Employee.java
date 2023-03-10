package com.example.carpark.employee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String account;

    @Column(length = 50)
    private String address;

    @Column
    private LocalDate birthdate;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String password;

    @Enumerated
    @Column(columnDefinition = "tinyint(1)")
    private EmployeeSexEnum sex;

    @Enumerated(EnumType.STRING)
    @Column
    private EmployeeDepartmentEnum department;


}
