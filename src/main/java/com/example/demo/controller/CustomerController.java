package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService svc;
    public CustomerController(CustomerService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody CustomerDto dto) { return svc.create(new Customer(dto.name(), dto.email())); }

    @GetMapping
    public List<Customer> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) { return svc.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody CustomerDto dto) {
        try { return svc.update(id, new Customer(dto.name(), dto.email())); }
        catch (IllegalArgumentException e) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
