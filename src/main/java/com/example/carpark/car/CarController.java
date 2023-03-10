package com.example.carpark.car;


import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import com.example.carpark.car.dto.request.CarRequest;
import com.example.carpark.car.model.Car;
import com.example.carpark.common.validate.annotations.IsEntityField;
import com.example.carpark.parkinglot.model.ParkingLot;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
@Validated
public class CarController {
    private final CarService carService;

    @PostMapping
    public Response create(@Valid @RequestBody CarRequest car) {
        Car createdCar = carService.create(car);
        return Response.success(createdCar);
    }

    @GetMapping("/{licensePlate}")
    public Response get(@PathVariable String licensePlate) {
        return Response.success(carService.getById(licensePlate));
    }

    @PutMapping
    public Response edit(@Valid @RequestBody CarRequest employee) {
        return Response.success(carService.edit(employee));
    }

    @DeleteMapping("/{licensePlate}")
    public Response delete(@PathVariable String licensePlate) {
        Car removed = carService.remove(licensePlate);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = Car.class) @RequestParam(value = "filterBy", required = false) String filterBy,
                           @RequestParam(value = "filterValue", required = false) String filterValue) {
        PaginationQuery paginationQuery = new PaginationQuery();
        if (page != null && size != null) {
            paginationQuery.setPageRequest(PageRequest.of(page, size));
        }
        if (filterBy != null && filterValue != null) {
            paginationQuery.setFilter(new PaginationQuery.Filter()
                    .setField(filterBy)
                    .setValue(filterValue));
        }
        return Response.paging(carService.getAll(paginationQuery));
    }
}
