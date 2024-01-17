package dat3.QuickBookTests;

import dat3.QuickBook.dto.ReservationRequest;
import dat3.QuickBook.dto.ReservationResponse;
import dat3.QuickBook.entity.Guest;
import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.ReservationRepository;
import dat3.QuickBook.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService reservationService;

    @BeforeEach
    public void init() {
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void createReservation() {
        // Mock data
        Hotel hotel = new Hotel(1L, "Test Hotel", "Test name", "Test street",
                "Test city", "Test Country", null, null, null);
        Room room = new Room(1, 1, 2, hotel, null, null, null);
        Guest guest = new Guest("Test firstname", "Test lastname", "Test phone",
                null, null, null);

        LocalDateTime reservationDate = LocalDateTime.now();

        ReservationRequest request = new ReservationRequest(1, reservationDate, room, guest, null, null);
        Reservation reservation = new Reservation(1, reservationDate, room, guest, null, null);

        // Mock the repository
        Mockito.when(reservationRepository.findByIdAndReservationDate(
                        request.getRoom().getId(), request.getReservationDate()))
                .thenReturn(Collections.emptyList());

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);


        // Execute the method
        ReservationResponse result = reservationService.createReservation(request);

        // Assert
        assertNotNull(result);
        assertEquals(reservation.getId(), result.getId());
        assertEquals(reservation.getGuest(), result.getGuest());
        assertEquals(reservation.getRoom(), result.getRoom());
        assertEquals(reservation.getReservationDate(), result.getReservationDate());
    }

    @Test
    public void isRoomAvailable() {
        // Mock data
        Hotel hotel = new Hotel(1L, "Test Hotel", "Test name", "Test street",
                "Test city", "Test Country", null, null, null);
        Room room = new Room(1, 1, 2, hotel, null, null, null);

        LocalDateTime reservationDate = LocalDateTime.now();

        // Mock behavior
        Mockito.when(reservationRepository.findByIdAndReservationDate(room.getId(), reservationDate))
                .thenReturn(Collections.singletonList(new Reservation(1, reservationDate, room, null, null, null)));

        // Execute the method
        boolean result = reservationService.isRoomAvailable(room, reservationDate);

        // Assert
        assertTrue(result);  // Room is not available since there's a reservation on the specified date
    }


    @Test
    public void cancelReservation() {
        LocalDateTime reservationDate = LocalDateTime.now();
        // Mock data
        Reservation reservation = new Reservation(1, reservationDate, null, null, null, null);

        // Mock behavior
        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        // Execute the method
        reservationService.cancelReservation(1);

        // Assert
        // Verify that the delete method was called with the correct reservation instance
        Mockito.verify(reservationRepository, Mockito.times(1)).delete(reservation);
    }

}
