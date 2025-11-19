package com.example.cinema.repository;
import com.example.cinema.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ScreeningRepository extends JpaRepository<Screening, Long>{}
