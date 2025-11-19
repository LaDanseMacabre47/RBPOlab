package com.example.cinema.repository;
import com.example.cinema.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long>{}
