package dat3.QuickBook.dto;

import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {
    private int id;
    private int roomNumber;
    private int numberOfBeds;
    private Hotel hotel;
    List<Reservation> reservations;
    private LocalDateTime created;
    private LocalDateTime updated;

    public RoomResponse(Room room, boolean includeDates){
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.hotel = room.getHotel();
        this.reservations = room.getReservations();
        if (includeDates) {
            this.created = room.getCreated();
            this.updated = room.getUpdated();
        }
    }
}
