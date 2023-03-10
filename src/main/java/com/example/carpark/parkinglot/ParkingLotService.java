package com.example.carpark.parkinglot;



import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.parkinglot.dto.request.ParkingLotRequest;
import com.example.carpark.parkinglot.model.ParkingLot;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;


    public ParkingLot create(ParkingLotRequest parkingLotRequest) {
        ParkingLot parkingLot = new ParkingLot()
                .setParkingName(parkingLotRequest.getParkingName())
                .setArea(parkingLotRequest.getArea())
                .setPrice(parkingLotRequest.getPrice())
                .setPlace(parkingLotRequest.getPlace());
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot edit(ParkingLotRequest parkingLotRequest, Long id) {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findById(id);
        if (!parkingLotOptional.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        ParkingLot parkingLot = parkingLotOptional.get()
                .setParkingName(parkingLotRequest.getParkingName())
                .setArea(parkingLotRequest.getArea())
                .setPrice(parkingLotRequest.getPrice())
                .setPlace(parkingLotRequest.getPlace());

        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot getById(Long id) {
        return parkingLotRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public ParkingLot remove(Long id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
        parkingLotRepository.delete(parkingLot);
        return parkingLot;
    }

    public Page<ParkingLot> getAll(PaginationQuery paginationQuery) {
        Pageable pageable = paginationQuery.getPageRequest();
        return parkingLotRepository.getAllAndFilter(paginationQuery.getFilter(), pageable);
    }

    RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.PARKING_LOT, exceptionType);
    }
}
