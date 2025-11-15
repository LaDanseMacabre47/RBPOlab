package com.example.demo.service;
import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MovieService {
    private final MovieRepository repo;
    public MovieService(MovieRepository repo){this.repo=repo;} 
    public Movie create(Movie m){return repo.save(m);} 
    public List<Movie> all(){return repo.findAll();}
    public java.util.Optional<Movie> get(Long id){return repo.findById(id);} 
    public Movie update(Long id, Movie update){return repo.findById(id).map(e->{e.setTitle(update.getTitle()); e.setDurationMinutes(update.getDurationMinutes()); return repo.save(e);}).orElseThrow(()->new IllegalArgumentException("Movie not found"));}
    public void delete(Long id){repo.deleteById(id);} }
