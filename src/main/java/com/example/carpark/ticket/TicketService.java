package com.example.carpark.ticket;



import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.car.CarRepository;
import com.example.carpark.car.model.Car;
import com.example.carpark.ticket.dto.request.TicketRequest;
import com.example.carpark.ticket.model.Ticket;
import com.example.carpark.trip.TripRepository;
import com.example.carpark.trip.model.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CarRepository carRepository;
    private final TripRepository tripRepository;


    public Ticket create(TicketRequest ticketRequest) {
        Optional<Car> optionalCar = carRepository.findByLicensePlate(ticketRequest.getCarLicensePlate());
        if (!optionalCar.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Optional<Trip> optionalTrip = tripRepository.findById(ticketRequest.getTripId());
        if (!optionalTrip.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Trip trip = optionalTrip.get();
        int currentNumberOfTickets = trip.getTickets().size();
        if(currentNumberOfTickets >= trip.getMaximumOnlineTicketNumber()) {
            throw new CarParkException.CommonLogicExeption("Maximum number of tickets for this trip is reached");
        }
        Ticket ticket = new Ticket().setCustomerName(ticketRequest.getCustomerName())
                .setTrip(optionalTrip.get()).setCar(optionalCar.get())
                .setBookingTime(ticketRequest.getBookingTime());
        return ticketRepository.save(ticket);
    }

    public Ticket edit(TicketRequest ticketRequest, Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (!ticketOptional.isPresent()) {
            throw CarParkException.throwException(EntityType.PARKING_LOT, ExceptionType.ENTITY_NOT_FOUND);
        }
        Optional<Car> optionalCar = carRepository.findByLicensePlate(ticketRequest.getCarLicensePlate());
        if (!optionalCar.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Optional<Trip> optionalTrip = tripRepository.findById(ticketRequest.getTripId());
        if (!optionalTrip.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Ticket ticket = ticketOptional.get().setCustomerName(ticketRequest.getCustomerName())
                .setTrip(optionalTrip.get()).setCar(optionalCar.get())
                .setBookingTime(ticketRequest.getBookingTime());
        return ticketRepository.save(ticket);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public Ticket remove(Long id) {
        Ticket ti = ticketRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
        ticketRepository.delete(ti);
        return ti;
    }

    public Page<Ticket> getAll(PaginationQuery paginationQuery) {
        Pageable pageable = paginationQuery.getPageRequest();
        return ticketRepository.getAllAndFilter(paginationQuery.getFilter(), pageable);
    }

    RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.TICKET, exceptionType);
    }
}
