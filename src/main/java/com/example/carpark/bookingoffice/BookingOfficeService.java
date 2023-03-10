package com.example.carpark.bookingoffice;

import com.example.carpark.bookingoffice.dto.request.BookingOfficeRequest;
import com.example.carpark.bookingoffice.model.BookingOffice;
import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.trip.TripRepository;
import com.example.carpark.trip.model.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingOfficeService {
    private final BookingOfficeRepository bookingOfficeRepository;
    private final TripRepository tripRepository;

    public BookingOffice getBookingOfficeById(Long id) {
        return bookingOfficeRepository.findById(id).
                orElseThrow(() -> throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public BookingOffice createBookingOffice(BookingOfficeRequest bookingOfficeRequest) {
        Optional<Trip> trip = tripRepository.findById(bookingOfficeRequest.getTripId());
        if (!trip.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        LocalDate startContractDeadline = bookingOfficeRequest.getStartContractDeadline();
        LocalDate endContractDeadline = bookingOfficeRequest.getEndContractDeadline();
        if (startContractDeadline.isAfter(endContractDeadline)) {
            throw new CarParkException.CommonLogicExeption("Start contract deadline must be before end contract deadline");
        }
        BookingOffice bookingOffice = new BookingOffice()
                .setOfficeName(bookingOfficeRequest.getOfficeName())
                .setOfficePrice(bookingOfficeRequest.getOfficePrice())
                .setTrip(trip.get())
                .setOfficePhone(bookingOfficeRequest.getOfficePhone())
                .setOfficePlace(bookingOfficeRequest.getOfficePlace())
                .setStartContractDeadline(bookingOfficeRequest.getStartContractDeadline())
                .setEndContractDeadline(bookingOfficeRequest.getEndContractDeadline());
        return bookingOfficeRepository.save(bookingOffice);
    }

    public BookingOffice editBookingOffice(BookingOfficeRequest bookingOfficeRequest, Long id) {
        Optional<BookingOffice> bookingOfficeOptional = bookingOfficeRepository.findById(id);
        if (!bookingOfficeOptional.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Optional<Trip> trip = tripRepository.findById(bookingOfficeRequest.getTripId());
        if (!trip.isPresent()) {
            throw throwTripException(ExceptionType.ENTITY_NOT_FOUND);
        }
        LocalDate startContractDeadline = bookingOfficeRequest.getStartContractDeadline();
        LocalDate endContractDeadline = bookingOfficeRequest.getEndContractDeadline();
        if (startContractDeadline.isAfter(endContractDeadline)) {
            throw new CarParkException.CommonLogicExeption("Start contract deadline must be before end contract deadline");
        }
        BookingOffice bookingOffice = new BookingOffice()
                .setOfficeName(bookingOfficeRequest.getOfficeName())
                .setOfficePrice(bookingOfficeRequest.getOfficePrice())
                .setTrip(trip.get())
                .setOfficePhone(bookingOfficeRequest.getOfficePhone())
                .setOfficePlace(bookingOfficeRequest.getOfficePlace())
                .setStartContractDeadline(bookingOfficeRequest.getStartContractDeadline())
                .setEndContractDeadline(bookingOfficeRequest.getEndContractDeadline());
        return bookingOfficeRepository.save(bookingOffice);
    }

    public BookingOffice removeBookingOffice(Long id) {
        Optional<BookingOffice> bookingOfficeOptional = bookingOfficeRepository.findById(id);
        if (!bookingOfficeOptional.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        bookingOfficeRepository.delete(bookingOfficeOptional.get());
        return bookingOfficeOptional.get();
    }

    public Page<BookingOffice> getBookingOfficeList(PaginationQuery paginationQuery) {
        return bookingOfficeRepository.getAllAndFilter(paginationQuery.getFilter(), paginationQuery.getPageRequest());
    }

    public RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.BOOKING_OFFICE, exceptionType);
    }

    public RuntimeException throwTripException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.TRIP, exceptionType);
    }

}
