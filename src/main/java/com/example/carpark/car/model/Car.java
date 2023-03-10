package com.example.carpark.car.model;


import com.example.carpark.parkinglot.model.ParkingLot;
import com.example.carpark.ticket.model.Ticket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.text.Caret;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Car {
    @Id
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column
    private CarCompanyEnum company;

    @Column
    private String carType;

    @Column
    private String carColor;

    @ManyToOne
    @JsonManagedReference
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<Ticket> tickets;

}
