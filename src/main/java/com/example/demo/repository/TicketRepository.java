package com.example.demo.repository;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByScreening(Screening screening);
    List<Ticket> findByScreening(Screening screening);
}
