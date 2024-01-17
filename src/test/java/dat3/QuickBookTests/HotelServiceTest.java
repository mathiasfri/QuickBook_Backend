package dat3.QuickBookTests;

import dat3.QuickBook.dto.HotelRequest;
import dat3.QuickBook.dto.HotelResponse;
import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.HotelRepository;
import dat3.QuickBook.service.HotelService;
import dat3.QuickBook.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    private HotelService hotelService;

    @BeforeEach
    public void init() {
        hotelService = new HotelService(hotelRepository);
    }

    @Test
    public void createHotel() {
        List<Room> roomList = new ArrayList<>();
        // Mock data
        Hotel hotel = new Hotel(1L, "Test Hotel", "Test Street", "Test City",
                "12345", "Test Country", roomList, null, null);
        HotelRequest hotelRequest = new HotelRequest(hotel);

        // Mock behavior
        Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotel);

        // Execute the method
        HotelResponse result = hotelService.createHotel(hotelRequest);

        // Assert
        assertEquals(hotel.getName(), result.getName());
        assertEquals(hotel.getStreet(), result.getStreet());
        assertEquals(hotel.getCity(), result.getCity());
    }

    @Test
    public void getAllHotels() {
        List<Room> roomList = new ArrayList<>();
        // Mock data
        List<Hotel> mockHotels = Arrays.asList(
                new Hotel(1L, "Hotel 1", "Street 1", "City 1", "12345", "Country 1", roomList, null, null),
                new Hotel(2L, "Hotel 2", "Street 2", "City 2", "54321", "Country 2", roomList, null, null)
        );

        // Mock behavior
        Mockito.when(hotelRepository.findAll()).thenReturn(mockHotels);

        // Execute the method
        List<HotelResponse> result = hotelService.getAllHotels(true);

        // Assert
        assertEquals(mockHotels.size(), result.size());
    }

    @Test
    public void updateHotel() {
        List<Room> roomList = new ArrayList<>();
        // Mock data
        Hotel existingHotel = new Hotel(1L, "Existing Hotel", "Existing Street", "Existing City",
                "12345", "Existing Country", roomList, null, null);

        HotelRequest updatedHotelRequest = new HotelRequest("Updated Hotel", "Updated Street", "Updated City",
                "54321", "Updated Country", 3, null, null);

        // Mock behavior
        Mockito.when(hotelRepository.findById(existingHotel.getId())).thenReturn(existingHotel);
        Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenAnswer(invocation -> {
            Hotel savedHotel = invocation.getArgument(0);
            savedHotel.setUpdated(LocalDateTime.now());
            return savedHotel;
        });

        // Execute the method
        HotelResponse result = hotelService.updateHotel(updatedHotelRequest, existingHotel.getId());

        // Assert
        assertEquals(updatedHotelRequest.getName(), result.getName());
        assertEquals(updatedHotelRequest.getStreet(), result.getStreet());
        assertEquals(updatedHotelRequest.getCity(), result.getCity());
        assertEquals(updatedHotelRequest.getZip(), result.getZip());
        assertEquals(updatedHotelRequest.getCountry(), result.getCountry());
        assertNotNull(result.getUpdated());
    }

    @Test
    public void deleteHotel() {
        // Mock data
        Long hotelId = 1L;
        Hotel hotelToDelete = new Hotel(hotelId, "Test Hotel", "Test Street", "Test City",
                "12345", "Test Country", null, null, null);

        // Mock behavior
        Mockito.when(hotelRepository.findById(hotelId)).thenReturn(hotelToDelete);

        // Execute the method
        hotelService.deleteHotel(hotelId);

        // Verify that delete method was called with the correct hotel
        Mockito.verify(hotelRepository).delete(hotelToDelete);
    }
}

