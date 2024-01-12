package dat3.QuickBook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @Column(unique = true, nullable = false)
    private int id;
    private LocalDateTime reservationDate;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Guest guest;

    private LocalDateTime created;
    private LocalDateTime updated;
}
