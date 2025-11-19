package com.example.cinema.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Screening {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="movie_id") private Movie movie;
    private LocalDateTime startTime;
    private Double price;
    public Screening(){}
    public Long getId(){return id;}
    public Movie getMovie(){return movie;}
    public void setMovie(Movie m){this.movie=m;}
    public LocalDateTime getStartTime(){return startTime;}
    public void setStartTime(LocalDateTime t){this.startTime=t;}
    public Double getPrice(){return price;}
    public void setPrice(Double p){this.price=p;}
}
