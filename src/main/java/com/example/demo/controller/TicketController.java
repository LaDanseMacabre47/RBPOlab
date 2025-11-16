package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketRepository repo;
    private final ScreeningRepository screeningRepo;

    @GetMapping("/popular-seats")
    public List<Object[]> popularSeats() {
        return repo.popularSeats();
    }

    @GetMapping("/bookings-per-movie")
    public List<Object[]> bookingsPerMovie() {
        return repo.bookingsPerMovie();
    }

    @GetMapping("/top-buyers")
    public List<Object[]> topBuyers(@RequestParam(defaultValue = "5") int limit) {
        return repo.topBuyers(PageRequest.of(0, limit));
    }

    @GetMapping("/screenings/high-occupancy")
    public List<Long> highOccupancy(@RequestParam(defaultValue = "70") int percent) {

        List<Object[]> stats = screeningRepo.occupancyStats();

        List<Long> result = new ArrayList<>();

        for (Object[] row : stats) {
            Long screeningId = (Long) row[0];
            Long booked = (Long) row[1];
            Integer seats = (Integer) row[2];

            double occupancy = (booked * 100.0) / seats;

            if (occupancy >= percent)
                result.add(screeningId);
        }

        return result;
    }
}