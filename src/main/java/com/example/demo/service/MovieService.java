package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository repo;
    public MovieService(MovieRepository repo) { this.repo = repo; }
    public Movie create(Movie m) { return repo.save(m); }
    public List<Movie> all() { return repo.findAll(); }
    public Optional<Movie> get(Long id) { return repo.findById(id); }
    public Movie update(Long id, Movie update) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(update.getTitle());
            existing.setDurationMinutes(update.getDurationMinutes());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
