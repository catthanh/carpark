package com.example.carpark.bookingoffice.model;

import com.example.carpark.trip.model.Trip;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class BookingOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate startContractDeadline;

    @Column
    private LocalDate endContractDeadline;

    @Column
    private String officeName;

    @Column
    private String officePhone;

    @Enumerated(EnumType.STRING)
    @Column
    private BookingOfficePlaceEnum officePlace;

    @Column
    private String officePrice;

    @ManyToOne
    @JsonManagedReference
    private Trip trip;
}
