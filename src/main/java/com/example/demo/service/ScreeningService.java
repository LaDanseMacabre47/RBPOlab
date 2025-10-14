package com.example.demo.service;

import com.example.demo.entity.Screening;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Hall;
import com.example.demo.repository.ScreeningRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    private final ScreeningRepository repo;
    private final MovieService movieService;
    private final HallService hallService;

    public ScreeningService(ScreeningRepository repo, MovieService movieService, HallService hallService) {
        this.repo = repo; this.movieService = movieService; this.hallService = hallService;
    }

    public Screening create(Screening s) {
        // ensure movie and hall exist
        Long movieId = s.getMovie().getId();
        Long hallId = s.getHall().getId();
        Movie movie = movieService.get(movieId).orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));
        Hall hall = hallService.get(hallId).orElseThrow(() -> new IllegalArgumentException("Hall not found: " + hallId));
        s.setMovie(movie);
        s.setHall(hall);
        return repo.save(s);
    }

    public List<Screening> all() { return repo.findAll(); }
    public Optional<Screening> get(Long id) { return repo.findById(id); }

    public Screening update(Long id, Screening update) {
        return repo.findById(id).map(existing -> {
            if (update.getMovie() != null) {
                Long movieId = update.getMovie().getId();
                existing.setMovie(movieService.get(movieId).orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId)));
            }
            if (update.getHall() != null) {
                Long hallId = update.getHall().getId();
                existing.setHall(hallService.get(hallId).orElseThrow(() -> new IllegalArgumentException("Hall not found: " + hallId)));
            }
            if (update.getStartTime() != null) existing.setStartTime(update.getStartTime());
            if (update.getPrice() != null) existing.setPrice(update.getPrice());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Screening not found: " + id));
    }

    public void delete(Long id) { repo.deleteById(id); }
}
