package com.example.demo.repository;
import com.example.demo.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findByHallId(Long hallId);
}
