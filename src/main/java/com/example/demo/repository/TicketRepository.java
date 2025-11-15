package com.example.demo.repository;
import com.example.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByScreeningId(Long screeningId);
    boolean existsByScreeningIdAndSeatNumber(Long screeningId, Integer seatNumber);
    List<Ticket> findByScreeningId(Long screeningId);
}
