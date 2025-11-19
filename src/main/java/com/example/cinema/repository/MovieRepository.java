package com.example.cinema.repository;
import com.example.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieRepository extends JpaRepository<Movie, Long>{}
