package dat3.QuickBook.entity;

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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    private LocalDateTime created;
    private LocalDateTime updated;
}
