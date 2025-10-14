package com.example.demo.controller;

import com.example.demo.dto.ScreeningDto;
import com.example.demo.entity.Screening;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Hall;
import com.example.demo.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {
    private final ScreeningService svc;
    public ScreeningController(ScreeningService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Screening create(@RequestBody ScreeningDto dto) {
        try {
            LocalDateTime st = LocalDateTime.parse(dto.startTime());
            Screening s = new Screening(new Movie(), new Hall(), st, dto.price());
            s.getMovie().setId(dto.movieId());
            s.getHall().setId(dto.hallId());
            return svc.create(s);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid startTime, use ISO format: yyyy-MM-ddTHH:mm");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<Screening> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Screening get(@PathVariable Long id) { return svc.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); }

    @PutMapping("/{id}")
    public Screening update(@PathVariable Long id, @RequestBody ScreeningDto dto) {
        try {
            Screening s = new Screening();
            if (dto.movieId() != null) { Movie m = new Movie(); m.setId(dto.movieId()); s.setMovie(m); }
            if (dto.hallId() != null)  { Hall h = new Hall(); h.setId(dto.hallId()); s.setHall(h); }
            if (dto.startTime() != null) s.setStartTime(LocalDateTime.parse(dto.startTime()));
            if (dto.price() != null) s.setPrice(dto.price());
            return svc.update(id, s);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
