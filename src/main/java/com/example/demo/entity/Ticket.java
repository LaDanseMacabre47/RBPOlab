package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Screening screening;

    @ManyToOne(optional = false)
    private Customer customer;

    private Integer seatNumber; // optional
    private Double price;
    private LocalDateTime purchasedAt;

    public Ticket() {}

    public Ticket(Screening screening, Customer customer, Integer seatNumber, Double price, LocalDateTime purchasedAt) {
        this.screening = screening;
        this.customer = customer;
        this.seatNumber = seatNumber;
        this.price = price;
        this.purchasedAt = purchasedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Screening getScreening() { return screening; }
    public void setScreening(Screening screening) { this.screening = screening; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Integer getSeatNumber() { return seatNumber; }
    public void setSeatNumber(Integer seatNumber) { this.seatNumber = seatNumber; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public LocalDateTime getPurchasedAt() { return purchasedAt; }
    public void setPurchasedAt(LocalDateTime purchasedAt) { this.purchasedAt = purchasedAt; }
}
