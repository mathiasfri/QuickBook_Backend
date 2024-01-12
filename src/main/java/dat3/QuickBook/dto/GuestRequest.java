package dat3.QuickBook.dto;

import dat3.QuickBook.entity.Guest;
import dat3.QuickBook.entity.Reservation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Reservation> reservations;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static GuestRequest getUserRequest(Guest guest) {
        return new GuestRequest(guest.getUsername(), guest.getPassword(), guest.getFirstName(), guest.getLastName(), guest.getEmail(),
                guest.getPhoneNumber(), guest.getReservations(), guest.getCreated(), guest.getUpdated());
    }

    public GuestRequest(Guest guest) {
        this.username = guest.getUsername();
        this.password = guest.getPassword();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.email = guest.getEmail();
        this.phoneNumber = guest.getPhoneNumber();
        this.created = guest.getCreated();
        this.updated = guest.getUpdated();
    }
}
