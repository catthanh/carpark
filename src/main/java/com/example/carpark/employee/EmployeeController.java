package com.example.carpark.employee;

import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import com.example.carpark.common.validate.annotations.IsEntityField;
import com.example.carpark.employee.dto.request.CreateEmployeeRequest;
import com.example.carpark.employee.model.Employee;
import com.example.carpark.parkinglot.model.ParkingLot;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public Response createEmployee(
            @Valid
            @RequestBody CreateEmployeeRequest employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return Response.success(createdEmployee);
    }

    @GetMapping("/{id}")
    public Response getEmployee(@PathVariable Long id) {
        return Response.success(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public Response editEmployee(@PathVariable Long id,
                                 @Valid @RequestBody CreateEmployeeRequest employee) {
        return Response.success(employeeService.editEmployee(employee, id));
    }

    @DeleteMapping("/{id}")
    public Response deleteEmployee(@PathVariable Long id) {
        Employee removed = employeeService.removeEmployee(id);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = Employee.class) @RequestParam(value = "filterBy", required = false) String filterBy,
                           @RequestParam(value = "filterValue", required = false) String filterValue) {
        PaginationQuery paginationQuery = new PaginationQuery();
        if (page != null && size != null) {
            paginationQuery.setPageRequest(PageRequest.of(page, size));
        }
        if (filterBy != null && filterValue != null) {
            paginationQuery.setFilter(new PaginationQuery.Filter()
                    .setField(filterBy)
                    .setValue(filterValue));
        }
        return Response.paging(employeeService.getAllEmployee(paginationQuery));
    }
}
