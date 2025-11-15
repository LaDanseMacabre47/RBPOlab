package com.example.demo.controller;

import com.example.demo.entity.Hall;
import com.example.demo.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    private final HallService svc;
    public HallController(HallService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hall create(@RequestBody Hall hall) { return svc.create(hall); }

    @GetMapping
    public List<Hall> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Hall get(@PathVariable Long id) { return svc.get(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Hall update(@PathVariable Long id, @RequestBody Hall hall) { return svc.update(id, hall); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
