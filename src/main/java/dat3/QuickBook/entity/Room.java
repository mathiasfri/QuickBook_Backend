package dat3.QuickBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roomNumber;
    private int numberOfBeds;
    @ManyToOne
    private Hotel hotel;
    @OneToMany(mappedBy = "room")
    List<Reservation> reservations;

    private LocalDateTime created;
    private LocalDateTime updated;
}
