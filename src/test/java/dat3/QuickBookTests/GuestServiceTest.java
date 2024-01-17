package dat3.QuickBookTests;

import dat3.QuickBook.dto.GuestRequest;
import dat3.QuickBook.dto.GuestResponse;
import dat3.QuickBook.entity.Guest;
import dat3.QuickBook.repository.GuestRepository;
import dat3.QuickBook.service.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    private GuestService guestService;

    @BeforeEach
    public void init() {
        guestService = new GuestService(guestRepository);
    }

    @Test
    public void createGuest() {
        // Mock data
        Guest guest = new Guest("testUsername", "John", "3452535", null, null, null);
        GuestRequest guestRequest = new GuestRequest(guest);
        guestRequest.setPassword("testPassword");

        // Mock behavior
        Mockito.when(guestRepository.save(Mockito.any(Guest.class))).thenReturn(guest);

        // Execute the method
        GuestResponse result = guestService.createGuest(guestRequest);

        // Assert
        assertNotNull(result);
        assertEquals(guest.getUsername(), result.getUsername());
        assertEquals(guest.getFirstName(), result.getFirstName());
        assertEquals(guest.getLastName(), result.getLastName());
        assertEquals(guest.getEmail(), result.getEmail());
    }

}

