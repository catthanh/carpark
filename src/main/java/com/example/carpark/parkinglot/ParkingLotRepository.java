package com.example.carpark.parkinglot;

import com.example.carpark.common.repository.CustomRepository;
import com.example.carpark.parkinglot.model.ParkingLot;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParkingLotRepository extends CustomRepository<ParkingLot, Long> {
    Optional<ParkingLot> findById(Long id);
}
