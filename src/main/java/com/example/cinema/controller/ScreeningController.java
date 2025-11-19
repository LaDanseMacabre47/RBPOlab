package com.example.cinema.controller;
import com.example.cinema.entity.Screening;
import com.example.cinema.repository.ScreeningRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {
    private final ScreeningRepository repo;
    public ScreeningController(ScreeningRepository repo){this.repo=repo;}
    @PostMapping public Screening create(@RequestBody Screening s){return repo.save(s);}
    @GetMapping public List<Screening> all(){return repo.findAll();}
}
