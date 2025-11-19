package com.example.cinema.controller;
import com.example.cinema.entity.Movie;
import com.example.cinema.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieRepository repo;
    public MovieController(MovieRepository repo){this.repo=repo;}
    @PostMapping public Movie create(@RequestBody Movie m){return repo.save(m);}
    @GetMapping public List<Movie> all(){return repo.findAll();}
}
