package dat3.QuickBook.service;

import dat3.QuickBook.dto.RoomRequest;
import dat3.QuickBook.dto.RoomResponse;
import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.HotelRepository;
import dat3.QuickBook.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }


    public RoomResponse createRoom(Long hotelId, RoomRequest roomRequest) {
        Hotel hotel = hotelRepository.findById(hotelId);

        if (hotel != null) {
            Room room = new Room();
            room.setRoomNumber(roomRequest.getRoomNumber());
            room.setNumberOfBeds(roomRequest.getNumberOfBeds());
            room.setHotel(hotel);
            room.setCreated(roomRequest.getCreated());
            room.setUpdated(roomRequest.getUpdated());

            Room savedRoom = roomRepository.save(room);

            return new RoomResponse(savedRoom, true);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
    }
}
