package com.example.demo.repository;
import com.example.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t.seatNumber, COUNT(t) FROM Ticket t GROUP BY t.seatNumber ORDER BY COUNT(t) DESC")
    List<Object[]> popularSeats();

    @Query("SELECT t.screening.movie.title, COUNT(t) FROM Ticket t GROUP BY t.screening.movie.title")
    List<Object[]> bookingsPerMovie();

    @Query("SELECT t.customer.name, COUNT(t) FROM Ticket t GROUP BY t.customer.name ORDER BY COUNT(t) DESC")
    List<Object[]> topBuyers(Pageable pageable);
}
