package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository repo;
    private final ScreeningService screeningService;
    private final CustomerService customerService;

    public TicketService(TicketRepository repo, ScreeningService screeningService, CustomerService customerService) {
        this.repo = repo; this.screeningService = screeningService; this.customerService = customerService;
    }

    @Transactional
    public Ticket create(Long screeningId, Long customerId, Integer seatNumber) {
        Screening screening = screeningService.get(screeningId).orElseThrow(() -> new IllegalArgumentException("Screening not found: " + screeningId));
        Customer customer = customerService.get(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        // check capacity
        long sold = repo.countByScreening(screening);
        int capacity = screening.getHall().getCapacity();
        if (sold >= capacity) {
            throw new IllegalStateException("No seats available for screening " + screeningId);
        }

        // optionally check seatNumber uniqueness (not implemented here), you may extend.

        Ticket t = new Ticket(screening, customer, seatNumber, screening.getPrice(), LocalDateTime.now());
        return repo.save(t);
    }

    public List<Ticket> all() { return repo.findAll(); }
    public Optional<Ticket> get(Long id) { return repo.findById(id); }

    @Transactional
    public void delete(Long ticketId) {
        Ticket ticket = repo.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + ticketId));
        LocalDateTime now = LocalDateTime.now();
        if (!ticket.getScreening().getStartTime().isAfter(now)) {
            throw new IllegalStateException("Cannot refund ticket after screening has started");
        }
        repo.deleteById(ticketId);
    }

    // update (e.g., change seat or customer) - simple example:
    @Transactional
    public Ticket update(Long id, Integer newSeatNumber) {
        return repo.findById(id).map(t -> {
            t.setSeatNumber(newSeatNumber);
            return repo.save(t);
        }).orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + id));
    }

    public List<Ticket> byScreening(Screening s) { return repo.findByScreening(s); }
}
