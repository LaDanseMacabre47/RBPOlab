package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int duration; // минуты
}
