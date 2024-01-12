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
public class GuestResponse {
    private String username;
    String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Reservation> reservations;
    private LocalDateTime created;
    private LocalDateTime updated;

    public GuestResponse(Guest guest) {
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
