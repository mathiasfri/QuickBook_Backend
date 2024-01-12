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
public class ReservationRequest {
    private int id;
    private LocalDateTime reservationDate;
    private Room room;
    private Guest guest;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static ReservationRequest getReservationRequest(Reservation reservation){
    return new ReservationRequest(reservation.getId(), reservation.getReservationDate(), reservation.getRoom(),
        reservation.getGuest(), reservation.getCreated(), reservation.getUpdated());
    }

    public ReservationRequest(Reservation reservation){
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        this.room = reservation.getRoom();
        this.guest = reservation.getGuest();
        this.created = reservation.getCreated();
        this.updated = reservation.getUpdated();
    }
}
