package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m ORDER BY m.duration DESC")
    List<Movie> topLongest(Pageable pageable);
}