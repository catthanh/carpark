package com.example.carpark.bookingoffice;

import com.example.carpark.bookingoffice.dto.request.BookingOfficeRequest;
import com.example.carpark.bookingoffice.model.BookingOffice;
import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import com.example.carpark.common.validate.annotations.IsEntityField;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking-office")
@RequiredArgsConstructor
@Validated
public class BookingOfficeController {
    @Autowired
    BookingOfficeService bookingOfficeService;

    @PostMapping
    public Response create(@Valid @RequestBody BookingOfficeRequest bookingOfficeRequest) {
        BookingOffice bookingOffice = bookingOfficeService.createBookingOffice(bookingOfficeRequest);
        return Response.success(bookingOffice);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(bookingOfficeService.getBookingOfficeById(id));
    }

    @PutMapping("/{id}")
    public Response edit(@PathVariable Long id, @Valid @RequestBody BookingOfficeRequest bookingOfficeRequest) {
        return Response.success(bookingOfficeService.editBookingOffice(bookingOfficeRequest, id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        BookingOffice removed = bookingOfficeService.removeBookingOffice(id);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = BookingOffice.class) @RequestParam(value = "filterBy", required = false) String filterBy,
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
        return Response.paging(bookingOfficeService.getBookingOfficeList(paginationQuery));
    }
}
