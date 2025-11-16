package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Hall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int seats;
}