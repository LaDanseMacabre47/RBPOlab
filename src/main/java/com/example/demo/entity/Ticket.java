package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Screening screening;

    @ManyToOne
    private Customer customer;

    private int seatNumber;
}