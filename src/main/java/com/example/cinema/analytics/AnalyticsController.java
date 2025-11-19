package com.example.cinema.analytics;
import com.example.cinema.entity.*;
import com.example.cinema.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final MovieRepository movieRepo;
    private final ScreeningRepository screeningRepo;
    private final TicketRepository ticketRepo;
    private final CustomerRepository customerRepo;
    public AnalyticsController(MovieRepository movieRepo, ScreeningRepository screeningRepo, TicketRepository ticketRepo, CustomerRepository customerRepo){
        this.movieRepo=movieRepo; this.screeningRepo=screeningRepo; this.ticketRepo=ticketRepo; this.customerRepo=customerRepo;
    }
    @GetMapping("/top-longest-movies")
    public List<Movie> topLongest(@RequestParam(defaultValue="10") int n){
        return movieRepo.findAll().stream()
                .filter(m->m.getDurationMinutes()!=null)
                .sorted(Comparator.comparingInt(Movie::getDurationMinutes).reversed())
                .limit(n).collect(Collectors.toList());
    }
    @GetMapping("/top-revenue-screenings")
    public List<Map<String,Object>> revenuePerScreening(){
        Map<Long, Double> sums = ticketRepo.findAll().stream()
                .collect(Collectors.groupingBy(t->t.getScreening().getId(), Collectors.summingDouble(Ticket::getPrice)));
        List<Map<String,Object>> out = new ArrayList<>();
        for (var e: sums.entrySet()){
            screeningRepo.findById(e.getKey()).ifPresent(s->{
                Map<String,Object> m=new HashMap<>();
                m.put("screening", s);
                m.put("revenue", e.getValue());
                out.add(m);
            });
        }
        out.sort((a,b)->Double.compare((Double)b.get("revenue"), (Double)a.get("revenue")));
        return out;
    }
}
