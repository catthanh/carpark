package com.example.carpark.trip;



import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.trip.dto.request.TripRequest;
import com.example.carpark.trip.model.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;


    public Trip create(TripRequest tripRequest) {
        Trip trip = new Trip()
                .setDriver(tripRequest.getDriver())
                .setDestination(tripRequest.getDestination())
                .setCarType(tripRequest.getCarType())
                .setDepartureDate(tripRequest.getDepartureDate())
                .setDepartureTime(tripRequest.getDepartureTime())
                .setMaximumOnlineTicketNumber(tripRequest.getMaximumOnlineTicketNumber());
        return tripRepository.save(trip);
    }

    public Trip editEmployee(TripRequest tripRequest, Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (!tripOptional.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Trip trip = tripOptional.get()
                .setDriver(tripRequest.getDriver())
                .setDestination(tripRequest.getDestination())
                .setCarType(tripRequest.getCarType())
                .setDepartureDate(tripRequest.getDepartureDate())
                .setDepartureTime(tripRequest.getDepartureTime())
                .setMaximumOnlineTicketNumber(tripRequest.getMaximumOnlineTicketNumber());

        return tripRepository.save(trip);
    }

    public Trip getById(Long id) {
        return tripRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public Trip remove(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
        tripRepository.delete(trip);
        return trip;
    }

    public Page<Trip> getAll(PaginationQuery paginationQuery) {
        Pageable pageable = paginationQuery.getPageRequest();
        return tripRepository.getAllAndFilter(paginationQuery.getFilter(), pageable);
    }

    RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.TRIP, exceptionType);
    }
}
