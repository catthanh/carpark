package com.example.carpark.trip.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripRequest {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate departureDate;

    private LocalTime departureTime;

    @NotNull(message = "Destination is required")
    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Driver is required")
    @NotBlank(message = "Driver is required")
    private String driver;

    @NotNull(message = "Maximum online ticket number is required")
    @NotBlank(message = "Maximum online ticket number is required")
    private String carType;

    @NotNull(message = "Maximum online ticket number is required")
    @Positive(message = "Maximum online ticket number must be positive")
    private Integer maximumOnlineTicketNumber;

}
