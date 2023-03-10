package com.example.carpark.car;

import com.example.carpark.car.model.Car;
import com.example.carpark.common.repository.CustomRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CustomRepository<Car, Long> {
    Optional<Car> findByLicensePlate(String licensePlate);

    boolean existsByLicensePlate(String licensePlate);
}
