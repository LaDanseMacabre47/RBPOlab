package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository repo;

    @GetMapping("/top-duration")
    public List<Movie> topDuration(@RequestParam(defaultValue = "5") int limit) {
        return repo.topLongest(PageRequest.of(0, limit));
    }
}