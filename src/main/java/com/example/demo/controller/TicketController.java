package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
import com.example.demo.service.ScreeningService;
import com.example.demo.entity.Screening;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService svc;
    private final ScreeningService screeningService;

    public TicketController(TicketService svc, ScreeningService screeningService) {
        this.svc = svc; this.screeningService = screeningService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@RequestBody TicketDto dto) {
        try {
            return svc.create(dto.screeningId(), dto.customerId(), dto.seatNumber());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping
    public List<Ticket> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable Long id) { return svc.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            svc.delete(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody TicketDto dto) {
        try {
            return svc.update(id, dto.seatNumber());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // helper: list tickets by screening
    @GetMapping("/by-screening/{screeningId}")
    public List<Ticket> byScreening(@PathVariable Long screeningId) {
        Screening s = screeningService.get(screeningId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return svc.byScreening(s);
    }
}
