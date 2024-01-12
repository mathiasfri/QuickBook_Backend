package dat3.QuickBook.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class Guest extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;

    private LocalDateTime created;
    private LocalDateTime updated;
}
