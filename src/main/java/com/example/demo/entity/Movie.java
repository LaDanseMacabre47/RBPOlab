package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Duration;

@Entity
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer durationMinutes; // duration in minutes

    // constructors, getters, setters
    public Movie() {}
    public Movie(String title, Integer durationMinutes) {
        this.title = title;
        this.durationMinutes = durationMinutes;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
