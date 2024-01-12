package dat3.QuickBook.dto;

import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Room;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    private int rooms;
    private LocalDateTime created;
    private LocalDateTime updated;

    public HotelResponse(Hotel hotel, boolean includeDates){
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.city = hotel.getCity();
        this.zip = hotel.getZip();
        this.country = hotel.getCountry();
        this.rooms = hotel.getRooms().size();
        if (includeDates) {
            this.created = hotel.getCreated();
            this.updated = hotel.getUpdated();
        }
    }

    public HotelResponse(Hotel hotel){
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.city = hotel.getCity();
        this.zip = hotel.getZip();
        this.country = hotel.getCountry();
        this.rooms = hotel.getRooms().size();
        this.created = hotel.getCreated();
        this.updated = hotel.getUpdated();
    }
}
