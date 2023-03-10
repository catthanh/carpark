package com.example.carpark.ticket;

import com.example.carpark.common.repository.CustomRepository;
import com.example.carpark.ticket.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketRepository extends CustomRepository<Ticket, Long> {
    Optional<Ticket> findById(Long id);
}
