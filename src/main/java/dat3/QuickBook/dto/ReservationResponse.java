package dat3.QuickBook.dto;

import dat3.QuickBook.entity.Guest;
import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private int id;
    private LocalDateTime reservationDate;
    private Room room;
    private Guest guest;
    private LocalDateTime created;
    private LocalDateTime updated;

    public ReservationResponse(Reservation reservation){
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        this.room = reservation.getRoom();
        this.guest = reservation.getGuest();
        this.created = reservation.getCreated();
        this.updated = reservation.getUpdated();
    }
}
