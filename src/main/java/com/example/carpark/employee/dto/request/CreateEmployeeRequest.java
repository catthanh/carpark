package com.example.carpark.employee.dto.request;

import com.example.carpark.employee.model.EmployeeDepartmentEnum;
import com.example.carpark.employee.model.EmployeeSexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmployeeRequest {
    @NotNull(message = "Account is required")
    @NotBlank(message = "Account is required")
    @Size(min = 4, max = 20)
    private String account;

    private EmployeeDepartmentEnum department;

    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    private String address;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birthdate;

    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone is not valid")
    private String phone;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,20}$",
            message = "Password must include uppercase, lowercase and number.")
    private String password;

    private EmployeeSexEnum sex;


}
