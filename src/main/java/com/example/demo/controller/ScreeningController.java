package com.example.demo.controller;

import com.example.demo.entity.Screening;
import com.example.demo.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {
    private final ScreeningService svc;
    public ScreeningController(ScreeningService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Screening create(@RequestBody Screening s) { return svc.create(s); }

    @GetMapping
    public List<Screening> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Screening get(@PathVariable Long id) { return svc.get(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Screening update(@PathVariable Long id, @RequestBody Screening s) { return svc.update(id, s); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
