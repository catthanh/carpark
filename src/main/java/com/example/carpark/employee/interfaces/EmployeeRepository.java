package com.example.carpark.employee.interfaces;

import com.example.carpark.common.repository.CustomRepository;
import com.example.carpark.employee.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends
        CustomRepository<Employee, Long> {
    Optional<Employee> findByAccount(String account);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhone(String phone);

    boolean existsByAccount(String account);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Object> findByAccountAndIdIsNot(String account, Long id);

    boolean existsByAccountAndIdIsNot(String account, Long id);

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByPhoneAndIdIsNot(String phone, Long id);
}
