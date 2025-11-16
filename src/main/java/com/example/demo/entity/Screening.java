package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Screening {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Hall hall;

    private LocalDateTime startTime;
}