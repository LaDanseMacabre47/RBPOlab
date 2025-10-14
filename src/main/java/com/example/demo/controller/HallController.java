package com.example.demo.controller;

import com.example.demo.dto.HallDto;
import com.example.demo.entity.Hall;
import com.example.demo.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    private final HallService svc;
    public HallController(HallService svc) { this.svc = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hall create(@RequestBody HallDto dto) { return svc.create(new Hall(dto.name(), dto.capacity())); }

    @GetMapping
    public List<Hall> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Hall get(@PathVariable Long id) { return svc.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); }

    @PutMapping("/{id}")
    public Hall update(@PathVariable Long id, @RequestBody HallDto dto) {
        try { return svc.update(id, new Hall(dto.name(), dto.capacity())); }
        catch (IllegalArgumentException e) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }
}
