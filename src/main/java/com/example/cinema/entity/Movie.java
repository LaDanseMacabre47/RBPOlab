package com.example.cinema.entity;
import jakarta.persistence.*;
@Entity
public class Movie {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String title;
    private Integer durationMinutes;
    public Movie() {}
    public Long getId(){return id;}
    public String getTitle(){return title;}
    public void setTitle(String t){this.title=t;}
    public Integer getDurationMinutes(){return durationMinutes;}
    public void setDurationMinutes(Integer d){this.durationMinutes=d;}
}
