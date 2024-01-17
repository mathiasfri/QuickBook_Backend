package dat3.QuickBookTests;

import dat3.QuickBook.dto.RoomRequest;
import dat3.QuickBook.dto.RoomResponse;
import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.HotelRepository;
import dat3.QuickBook.repository.RoomRepository;
import dat3.QuickBook.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    private RoomService roomService;

    @BeforeEach
    public void init() {
        roomService = new RoomService(roomRepository, hotelRepository);
    }

    @Test
    public void createRoom() {
        Hotel hotel = new Hotel(1L, "Test Hotel", "Test name", "Test street",
                "Test city", "Test Country", null, null, null);
        Room room = new Room(1, 1, 2, hotel, null, null, null);

        Mockito.when(hotelRepository.findById(1L)).thenReturn(hotel);
        Mockito.when(roomRepository.save(Mockito.any(Room.class))).thenReturn(room);

        RoomResponse result = roomService.createRoom(1L, RoomRequest.getRoomRequest(room));

        assertNotNull(result);
        assertEquals(room.getId(), result.getId());
        assertEquals(room.getRoomNumber(), result.getRoomNumber());
        assertEquals(room.getNumberOfBeds(), result.getNumberOfBeds());
        assertEquals(room.getHotel(), result.getHotel());
        assertEquals(room.getReservations(), result.getReservations());
        assertEquals(room.getCreated(), result.getCreated());
        assertEquals(room.getUpdated(), result.getUpdated());
    }
}
