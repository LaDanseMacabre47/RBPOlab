package com.example.cinema.controller;
import com.example.cinema.entity.Ticket;
import com.example.cinema.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketRepository repo;
    public TicketController(TicketRepository repo){this.repo=repo;}
    @PostMapping public Ticket create(@RequestBody Ticket t){return repo.save(t);}
    @GetMapping public List<Ticket> all(){return repo.findAll();}
}
