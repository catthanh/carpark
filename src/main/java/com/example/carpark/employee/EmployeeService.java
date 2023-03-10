package com.example.carpark.employee;


import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.employee.dto.request.CreateEmployeeRequest;
import com.example.carpark.employee.interfaces.EmployeeRepository;
import com.example.carpark.employee.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        if (employeeRepository.existsByAccount(createEmployeeRequest.getAccount())) {
            throw new CarParkException.EntityAlreadyExistsException("account already exists");
        }
        if (employeeRepository.existsByEmail(createEmployeeRequest.getEmail())) {
            throw new CarParkException.EntityAlreadyExistsException("email already exists");
        }
        if (employeeRepository.existsByPhone(createEmployeeRequest.getPhone())) {
            throw new CarParkException.EntityAlreadyExistsException("phone already exists");
        }
        Employee employee = new Employee()
                .setAccount(createEmployeeRequest.getAccount())
                .setPassword(createEmployeeRequest.getPassword())
                .setName(createEmployeeRequest.getName())
                .setEmail(createEmployeeRequest.getEmail())
                .setPhone(createEmployeeRequest.getPhone())
                .setAddress(createEmployeeRequest.getAddress())
                .setSex(createEmployeeRequest.getSex())
                .setDepartment(createEmployeeRequest.getDepartment())
                .setBirthdate(createEmployeeRequest.getBirthdate());

        return employeeRepository.save(employee);
    }

    public Employee editEmployee(CreateEmployeeRequest createEmployeeRequest, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        if (employeeRepository.existsByAccountAndIdIsNot(createEmployeeRequest.getAccount(), employeeOptional.get().getId())) {
            throw new CarParkException.EntityAlreadyExistsException("account already exists");
        }
        if (employeeRepository.existsByEmailAndIdIsNot(createEmployeeRequest.getEmail(), employeeOptional.get().getId())) {
            throw new CarParkException.EntityAlreadyExistsException("email already exists");
        }
        if (employeeRepository.existsByPhoneAndIdIsNot(createEmployeeRequest.getPhone(), employeeOptional.get().getId())) {
            throw new CarParkException.EntityAlreadyExistsException("phone already exists");
        }
        Employee employee = employeeOptional.get()
                .setAccount(createEmployeeRequest.getAccount())
                .setPassword(createEmployeeRequest.getPassword())
                .setName(createEmployeeRequest.getName())
                .setEmail(createEmployeeRequest.getEmail())
                .setPhone(createEmployeeRequest.getPhone())
                .setAddress(createEmployeeRequest.getAddress())
                .setSex(createEmployeeRequest.getSex())
                .setDepartment(createEmployeeRequest.getDepartment())
                .setBirthdate(createEmployeeRequest.getBirthdate());

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public Employee removeEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
        employeeRepository.delete(employee);
        return employee;
    }

    public Page<Employee> getAllEmployee(PaginationQuery paginationQuery) {
        Pageable pageable = paginationQuery.getPageRequest();

        return employeeRepository.getAllAndFilter(paginationQuery.getFilter(), pageable);
    }

    RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.EMPLOYEE, exceptionType);
    }
}
