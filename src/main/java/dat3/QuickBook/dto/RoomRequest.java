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
public class RoomRequest {
    private int id;
    private int roomNumber;
    private int numberOfBeds;
    private Hotel hotel;
    List<Reservation> reservations;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static RoomRequest getRoomRequest(Room room){
        return new RoomRequest(room.getId(), room.getRoomNumber(), room.getNumberOfBeds(), room.getHotel(),
                room.getReservations(), room.getCreated(), room.getUpdated());
    }

    public RoomRequest(Room room){
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.hotel = room.getHotel();
        this.reservations = room.getReservations();
        this.created = room.getCreated();
        this.updated = room.getUpdated();
    }
}
