package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService svc;
    public TicketController(TicketService svc) { this.svc = svc; }

    // Купить билет
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket buy(@RequestBody TicketDto dto) {
        return svc.create(dto.screeningId(), dto.customerId(), dto.seatNumber());
    }

    // CRUD
    @GetMapping
    public List<Ticket> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable Long id) { return svc.getOrThrow(id); }

    // Возврат билета (удаление)
    @DeleteMapping("/{id}")
    public void refund(@PathVariable Long id) { svc.refund(id); }

    // Изменить место (seat)
    @PutMapping("/{id}/seat")
    public Ticket changeSeat(@PathVariable Long id, @RequestBody TicketDto dto) {
        return svc.changeSeat(id, dto.seatNumber());
    }

    // Передать билет другому покупателю
    @PostMapping("/{id}/transfer")
    public Ticket transfer(@PathVariable Long id, @RequestBody Map<String,Long> body) {
        Long newCustomerId = body.get("newCustomerId");
        return svc.transfer(id, newCustomerId);
    }

    // Bulk reserve (резерв несколькими местами)
    @PostMapping("/bulk")
    public List<Ticket> bulkReserve(@RequestBody Map<String,Object> body) {
        Long screeningId = ((Number) body.get("screeningId")).longValue();
        Long customerId = ((Number) body.get("customerId")).longValue();
        @SuppressWarnings("unchecked")
        List<Integer> seats = (List<Integer>) body.get("seats");
        return svc.bulkReserve(screeningId, customerId, seats);
    }

    @GetMapping("/by-screening/{screeningId}")
    public List<Ticket> byScreening(@PathVariable Long screeningId) { return svc.byScreening(screeningId); }
}
