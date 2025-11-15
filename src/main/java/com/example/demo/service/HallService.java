package com.example.demo.service;
import com.example.demo.entity.Hall;
import com.example.demo.repository.HallRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class HallService { private final HallRepository repo; public HallService(HallRepository repo){this.repo=repo;} public Hall create(Hall h){return repo.save(h);} public List<Hall> all(){return repo.findAll();} public java.util.Optional<Hall> get(Long id){return repo.findById(id);} public Hall update(Long id, Hall update){return repo.findById(id).map(e->{ if(update.getName()!=null) e.setName(update.getName()); if(update.getCapacity()!=null) e.setCapacity(update.getCapacity()); return repo.save(e);}).orElseThrow(()->new IllegalArgumentException("Hall not found")); } public void delete(Long id){repo.deleteById(id);} }
