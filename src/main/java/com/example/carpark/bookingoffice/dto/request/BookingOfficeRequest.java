package com.example.carpark.bookingoffice.dto.request;

import com.example.carpark.bookingoffice.model.BookingOfficePlaceEnum;
import com.example.carpark.common.validate.annotations.EnumName;
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
public class BookingOfficeRequest {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startContractDeadline;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate endContractDeadline;

    @NotNull(message = "Booking office name is required")
    @NotBlank(message = "Booking office name is required")
    @Size(min = 3, max = 50, message = "Booking office name must be between 3 and 50 characters")
    private String officeName;

    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must be numeric")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String officePhone;

    private BookingOfficePlaceEnum officePlace;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be greater than or equal to zero")
    private String officePrice;

    @NotNull(message = "Trip is required")
    @Positive(message = "Trip id must be positive")
    private Long tripId;

}
