package com.example.carpark.parkinglot.dto.request;

import com.example.carpark.parkinglot.model.ParkingLotPlaceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
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
public class ParkingLotRequest {

    @NotNull(message = "parking lot name is required")
    @Size(min = 3, max = 50, message = "parking lot name must be between 3 and 50 characters")
    private String parkingName;

    private ParkingLotPlaceEnum place;

    @NotNull(message = "parking lot area is required")
    @Positive(message = "parking lot area must be positive")
    private Double area;

    @NotNull(message = "parking lot price is required")
    @PositiveOrZero(message = "parking lot price must be positive or zero")
    private Double price;
}
