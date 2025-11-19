package com.example.cinema.entity;
import jakarta.persistence.*;
@Entity
public class Customer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name;
    public Customer(){}
    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
}
