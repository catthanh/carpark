package com.example.carpark.parkinglot;


import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import com.example.carpark.common.validate.annotations.IsEntityField;
import com.example.carpark.parkinglot.dto.request.ParkingLotRequest;
import com.example.carpark.parkinglot.model.ParkingLot;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-lot")
@RequiredArgsConstructor
@Validated
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    @PostMapping
    public Response create(@Valid @RequestBody ParkingLotRequest parkingLot) {
        ParkingLot createdParkingLot = parkingLotService.create(parkingLot);
        return Response.success(createdParkingLot);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(parkingLotService.getById(id));
    }

    @PutMapping("/{id}")
    public Response edit(@PathVariable Long id, @Valid @RequestBody ParkingLotRequest employee) {
        return Response.success(parkingLotService.edit(employee, id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        ParkingLot removed = parkingLotService.remove(id);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = ParkingLot.class) @RequestParam(value = "filterBy", required = false) String filterBy,
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
        return Response.paging(parkingLotService.getAll(paginationQuery));
    }
}
