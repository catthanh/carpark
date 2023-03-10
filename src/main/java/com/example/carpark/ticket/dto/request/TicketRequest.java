package com.example.carpark.ticket.dto.request;

import com.example.carpark.car.model.CarCompanyEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequest {

    @NotNull(message = "Customer name is required")
    @NotBlank(message = "Customer name is required")
    private String customerName;

    private LocalDateTime bookingTime;

    @NotNull(message = "Car license plate is required")
    @NotBlank(message = "Car license plate is required")
    private String carLicensePlate;

    @NotNull(message = "Trip Id is required")
    @Positive(message = "trip Id must be positive")
    private Long tripId;
}
