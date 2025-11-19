package com.example.cinema.controller;
import com.example.cinema.entity.Customer;
import com.example.cinema.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo){this.repo=repo;}
    @PostMapping public Customer create(@RequestBody Customer c){return repo.save(c);}
    @GetMapping public List<Customer> all(){return repo.findAll();}
}
