package com.example.carpark.parkinglot.model;

import com.example.carpark.car.model.Car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String parkingName;

    @Enumerated(EnumType.STRING)
    @Column
    private ParkingLotPlaceEnum place;

    @Column
    private Double area;

    @Column
    private Double price;

    @OneToMany(mappedBy = "parkingLot")
    @JsonBackReference
    private List<Car> car;
}
