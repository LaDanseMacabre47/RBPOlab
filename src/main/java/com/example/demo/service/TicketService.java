package com.example.demo.service;
import com.example.demo.entity.*;
import com.example.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TicketService {
    private final TicketRepository repo;
    private final ScreeningService screeningService;
    private final CustomerService customerService;
    public TicketService(TicketRepository repo, ScreeningService screeningService, CustomerService customerService) { this.repo = repo; this.screeningService = screeningService; this.customerService = customerService; }
    @Transactional
    public Ticket create(Long screeningId, Long customerId, Integer seatNumber) {
        Screening screening = screeningService.get(screeningId).orElseThrow(() -> new IllegalArgumentException("Screening not found: " + screeningId));
        Customer customer = customerService.get(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        long sold = repo.countByScreeningId(screeningId);
        int capacity = screening.getHall().getCapacity() != null ? screening.getHall().getCapacity() : 0;
        if (sold >= capacity) throw new IllegalStateException("No seats available for screening " + screeningId);
        if (seatNumber != null && repo.existsByScreeningIdAndSeatNumber(screeningId, seatNumber)) throw new IllegalStateException("Seat already taken");
        Ticket t = new Ticket(screening, customer, seatNumber, screening.getPrice(), LocalDateTime.now());
        return repo.save(t);
    }
    public List<Ticket> all() { return repo.findAll(); }
    public Ticket getOrThrow(Long id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + id)); }
    @Transactional
    public void refund(Long ticketId) { Ticket ticket = getOrThrow(ticketId); if (!ticket.getScreening().getStartTime().isAfter(LocalDateTime.now())) throw new IllegalStateException("Cannot refund ticket after screening has started"); repo.delete(ticket); }
    @Transactional
    public Ticket changeSeat(Long ticketId, Integer newSeat) { Ticket t = getOrThrow(ticketId); if (!t.getScreening().getStartTime().isAfter(LocalDateTime.now())) throw new IllegalStateException("Cannot change seat after screening has started"); if (newSeat != null && repo.existsByScreeningIdAndSeatNumber(t.getScreening().getId(), newSeat)) throw new IllegalStateException("Seat already taken"); t.setSeatNumber(newSeat); return repo.save(t); }
    @Transactional
    public Ticket transfer(Long ticketId, Long newCustomerId) { Ticket t = getOrThrow(ticketId); Customer c = customerService.get(newCustomerId).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + newCustomerId)); t.setCustomer(c); return repo.save(t); }
    @Transactional
    public List<Ticket> bulkReserve(Long screeningId, Long customerId, List<Integer> seats) { Screening screening = screeningService.get(screeningId).orElseThrow(() -> new IllegalArgumentException("Screening not found: " + screeningId)); Customer customer = customerService.get(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId)); int capacity = screening.getHall().getCapacity(); long sold = repo.countByScreeningId(screeningId); if (sold + seats.size() > capacity) throw new IllegalStateException("Not enough seats"); for (Integer s : seats) { if (repo.existsByScreeningIdAndSeatNumber(screeningId, s)) throw new IllegalStateException("Seat " + s + " already taken"); } for (Integer s : seats) { repo.save(new Ticket(screening, customer, s, screening.getPrice(), LocalDateTime.now())); } return repo.findByScreeningId(screeningId); }
    public List<Ticket> byScreening(Long screeningId) { return repo.findByScreeningId(screeningId); }
}
