package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "screenings")
public class Screening {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(optional = false) @JoinColumn(name = "hall_id")
    private Hall hall;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    private Double price;
    public Screening() {}
    public Screening(Movie movie, Hall hall, LocalDateTime startTime, Double price) {
        this.movie = movie; this.hall = hall; this.startTime = startTime; this.price = price;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public Hall getHall() { return hall; }
    public void setHall(Hall hall) { this.hall = hall; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
