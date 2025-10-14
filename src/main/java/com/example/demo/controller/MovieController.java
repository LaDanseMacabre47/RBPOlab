package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;
    public MovieController(MovieService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody MovieDto dto) {
        return service.create(new Movie(dto.title(), dto.durationMinutes()));
    }

    @GetMapping
    public List<Movie> all() { return service.all(); }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return service.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody MovieDto dto) {
        try {
            return service.update(id, new Movie(dto.title(), dto.durationMinutes()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
