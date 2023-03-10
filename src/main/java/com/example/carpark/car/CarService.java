package com.example.carpark.car;



import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.exception.CarParkException;
import com.example.carpark.common.exception.EntityType;
import com.example.carpark.common.exception.ExceptionType;
import com.example.carpark.car.dto.request.CarRequest;
import com.example.carpark.car.model.Car;
import com.example.carpark.parkinglot.ParkingLotRepository;
import com.example.carpark.parkinglot.model.ParkingLot;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ParkingLotRepository parkingLotRepository;

    public Car create(CarRequest carRequest) {
        if(carRepository.existsByLicensePlate(carRequest.getLicensePlate())){
                throw new CarParkException.EntityAlreadyExistsException("Car with license plate " + carRequest.getLicensePlate() + " already exists");
        }
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(carRequest.getParkingLotId());
        if (!parkingLot.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Car car = new Car()
                .setCarColor(carRequest.getCarColor())
                .setCarType(carRequest.getCarType())
                .setLicensePlate(carRequest.getLicensePlate())
                .setCompany(carRequest.getCompany())
                .setParkingLot(parkingLot.get());
        return carRepository.save(car);
    }

    public Car edit(CarRequest carRequest) {
        Optional<Car> carOptional = carRepository.findByLicensePlate(carRequest.getLicensePlate());
        if (!carOptional.isPresent()) {
            throw CarParkException.throwException(EntityType.PARKING_LOT, ExceptionType.ENTITY_NOT_FOUND);
        }
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(carRequest.getParkingLotId());
        if (!parkingLot.isPresent()) {
            throw throwException(ExceptionType.ENTITY_NOT_FOUND);
        }
        Car car = carOptional.get()
                .setCarColor(carRequest.getCarColor())
                .setCarType(carRequest.getCarType())
                .setLicensePlate(carRequest.getLicensePlate())
                .setCompany(carRequest.getCompany())
                .setParkingLot(parkingLot.get());

        return carRepository.save(car);
    }

    public Car getById(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
    }

    public Car remove(String licensePlate) {
        Car car = carRepository.findByLicensePlate(licensePlate).orElseThrow(() ->throwException(ExceptionType.ENTITY_NOT_FOUND));
        carRepository.delete(car);
        return car;
    }

    public Page<Car> getAll(PaginationQuery paginationQuery) {
        Pageable pageable = paginationQuery.getPageRequest();
        return carRepository.getAllAndFilter(paginationQuery.getFilter(), pageable);
    }

    RuntimeException throwException(ExceptionType exceptionType) {
        return CarParkException.throwException(EntityType.CAR, exceptionType);
    }
}
