package com.example.demo.repository;
import com.example.demo.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("""
        SELECT s.id, COUNT(t), s.hall.seats
        FROM Screening s
        LEFT JOIN Ticket t ON t.screening.id = s.id
        GROUP BY s.id, s.hall.seats
    """)
    List<Object[]> occupancyStats();
}