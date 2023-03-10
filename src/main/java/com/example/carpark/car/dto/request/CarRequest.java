package com.example.carpark.car.dto.request;

import com.example.carpark.car.model.CarCompanyEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarRequest {

    private CarCompanyEnum company;

    @NotNull(message = "License plate is required")
    @NotBlank(message = "License plate is required")
    private String licensePlate;


    private String carType;

    private String carColor;

    @NotNull(message = "Parking lot id is required")
    @Positive(message = "Parking lot id must be positive")
    private Long parkingLotId;
}
