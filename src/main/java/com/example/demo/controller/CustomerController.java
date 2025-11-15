package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService svc;
    public CustomerController(CustomerService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer c) { return svc.create(c); }

    @GetMapping
    public List<Customer> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) { return svc.get(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer c) { return svc.update(id, c); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
