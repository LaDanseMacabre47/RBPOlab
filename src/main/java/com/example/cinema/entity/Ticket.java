package com.example.cinema.entity;
import jakarta.persistence.*;
@Entity
public class Ticket {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="screening_id") private Screening screening;
    @ManyToOne(optional=false) @JoinColumn(name="customer_id") private Customer customer;
    private Double price;
    public Ticket(){}
    public Long getId(){return id;}
    public Screening getScreening(){return screening;}
    public void setScreening(Screening s){this.screening=s;}
    public Customer getCustomer(){return customer;}
    public void setCustomer(Customer c){this.customer=c;}
    public Double getPrice(){return price;}
    public void setPrice(Double p){this.price=p;}
}
