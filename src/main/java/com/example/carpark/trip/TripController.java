package com.example.carpark.trip;

import com.example.carpark.common.validate.annotations.IsEntityField;
import com.example.carpark.parkinglot.model.ParkingLot;
import com.example.carpark.trip.dto.request.TripRequest;
import com.example.carpark.trip.model.Trip;
import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
@Validated
public class TripController {
    private final TripService tripService;

    @PostMapping
    public Response create(@Valid @RequestBody TripRequest trip) {
        Trip createdTrip = tripService.create(trip);
        return Response.success(createdTrip);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(tripService.getById(id));
    }

    @PutMapping("/{id}")
    public Response edit(@PathVariable Long id, @Valid @RequestBody TripRequest employee) {
        return Response.success(tripService.editEmployee(employee, id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        Trip removed = tripService.remove(id);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = Trip.class) @RequestParam(value = "filterBy", required = false) String filterBy,
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
        return Response.paging(tripService.getAll(paginationQuery));
    }
}
