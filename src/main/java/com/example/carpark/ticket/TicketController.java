package com.example.carpark.ticket;


import com.example.carpark.common.dto.PaginationQuery;
import com.example.carpark.common.dto.Response;
import com.example.carpark.common.validate.annotations.IsEntityField;
import com.example.carpark.ticket.dto.request.TicketRequest;
import com.example.carpark.ticket.model.Ticket;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
@Validated
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public Response create(@Valid @RequestBody TicketRequest ticket) {
        Ticket createdticket = ticketService.create(ticket);
        return Response.success(createdticket);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(ticketService.getById(id));
    }

    @PutMapping("/{id}")
    public Response edit(@PathVariable Long id, @Valid @RequestBody TicketRequest employee) {
        return Response.success(ticketService.edit(employee, id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        Ticket removed = ticketService.remove(id);
        return Response.success(removed);
    }

    @GetMapping
    public Response getAll(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size,
                           @IsEntityField(entityClass = Ticket.class) @RequestParam(value = "filterBy", required = false) String filterBy,
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
        return Response.paging(ticketService.getAll(paginationQuery));
    }
}
