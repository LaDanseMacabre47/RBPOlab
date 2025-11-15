package com.example.demo.service;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomerService { private final CustomerRepository repo; public CustomerService(CustomerRepository repo){this.repo=repo;} public Customer create(Customer c){return repo.save(c);} public List<Customer> all(){return repo.findAll();} public java.util.Optional<Customer> get(Long id){return repo.findById(id);} public Customer update(Long id, Customer update){return repo.findById(id).map(e->{ e.setName(update.getName()); e.setEmail(update.getEmail()); return repo.save(e);}).orElseThrow(()->new IllegalArgumentException("Customer not found")); } public void delete(Long id){repo.deleteById(id);} }
