package dat3.QuickBook.api;

import dat3.QuickBook.dto.ReservationRequest;
import dat3.QuickBook.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/create")
    public void createReservation(@RequestBody ReservationRequest body){
        reservationService.createReservation(body);
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelReservation(@PathVariable int id){
        reservationService.cancelReservation(id);
    }
}
