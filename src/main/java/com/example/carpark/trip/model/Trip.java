package com.example.carpark.trip.model;

import com.example.carpark.bookingoffice.model.BookingOffice;
import com.example.carpark.ticket.model.Ticket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalTime departureTime;

    @Column
    private String destination;

    @Column
    private String driver;

    @Column
    private String carType;

    @Column
    private Integer maximumOnlineTicketNumber;

    @OneToMany(mappedBy = "trip")
    @JsonBackReference
    private List<BookingOffice> trips;

    @OneToMany(mappedBy = "trip")
    @JsonBackReference
    private List<Ticket> tickets;
}
