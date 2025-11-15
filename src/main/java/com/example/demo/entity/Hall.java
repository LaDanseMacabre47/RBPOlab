package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "halls", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Hall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer capacity;
    public Hall() {}
    public Hall(String name, Integer capacity) { this.name = name; this.capacity = capacity; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
