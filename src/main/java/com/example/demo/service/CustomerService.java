package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo) { this.repo = repo; }
    public Customer create(Customer c) { return repo.save(c); }
    public List<Customer> all() { return repo.findAll(); }
    public Optional<Customer> get(Long id) { return repo.findById(id); }
    public Customer update(Long id, Customer update) {
        return repo.findById(id).map(existing -> {
            existing.setName(update.getName());
            existing.setEmail(update.getEmail());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
