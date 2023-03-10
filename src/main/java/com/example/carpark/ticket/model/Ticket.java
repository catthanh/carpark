package com.example.carpark.ticket.model;


import com.example.carpark.car.model.Car;
import com.example.carpark.car.model.CarCompanyEnum;
import com.example.carpark.parkinglot.model.ParkingLot;
import com.example.carpark.trip.model.Trip;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String customerName;

    @Column
    private LocalDateTime bookingTime;

    @ManyToOne
    @JsonManagedReference
    private Car car;

    @ManyToOne
    @JsonManagedReference
    private Trip trip;

}
