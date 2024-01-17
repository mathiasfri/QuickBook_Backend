package dat3.QuickBook.service;

import dat3.QuickBook.dto.HotelRequest;
import dat3.QuickBook.dto.HotelResponse;
import dat3.QuickBook.dto.RoomRequest;
import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponse createHotel(HotelRequest body){
        Hotel hotel = new Hotel();
        hotel.setName(body.getName());
        hotel.setStreet(body.getStreet());
        hotel.setCity(body.getCity());
        hotel.setZip(body.getZip());
        hotel.setCountry(body.getCountry());
        hotel.setRooms(createRooms(hotel, body.getRooms()));
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());

        return new HotelResponse(hotelRepository.save(hotel));
    }

    public List<Room> createRooms(Hotel hotel, int numberOfRooms) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < numberOfRooms; i++) {
            Room room = new Room();
            room.setRoomNumber(i + 1);
            room.setNumberOfBeds(1);
            room.setHotel(hotel);
            room.setCreated(LocalDateTime.now());
            room.setUpdated(LocalDateTime.now());

            rooms.add(room);
        }
        return rooms;
    }

    public List<HotelResponse> getAllHotels(boolean includeDates) {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> new HotelResponse(hotel, includeDates)).collect(Collectors.toList());
    }

    public HotelResponse getHotelByName(Long id, boolean includeDates){
        Hotel hotel = hotelRepository.findById(id);

        if (hotel == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel not found");
        }

        return new HotelResponse(hotel, includeDates);
    }

    public HotelResponse updateHotel(HotelRequest body, Long id){
        Hotel editHotel = hotelRepository.findById(id);

        if (editHotel == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }

        editHotel.setName(body.getName());
        editHotel.setStreet(body.getStreet());
        editHotel.setCity(body.getCity());
        editHotel.setZip(body.getZip());
        editHotel.setCountry(body.getCountry());

        // Update the number of rooms
        int requestedRooms = body.getRooms();
        int currentRooms = editHotel.getRooms().size();

        if (requestedRooms > currentRooms) {
            // Add rooms
            int roomsToAdd = requestedRooms - currentRooms;
            List<Room> rooms = createRooms(editHotel, roomsToAdd);
            editHotel.getRooms().addAll(rooms);
        } else if (requestedRooms < currentRooms) {
            // Remove rooms
            int roomsToRemove = currentRooms - requestedRooms;
            editHotel.getRooms().subList(currentRooms - roomsToRemove, currentRooms).clear();
        }

        editHotel.setUpdated(LocalDateTime.now());

        return new HotelResponse(hotelRepository.save(editHotel));
    }

    public void deleteHotel(Long id){
        Hotel hotel = hotelRepository.findById(id);

        if (hotel != null){
            hotelRepository.delete(hotel);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel not found");
        }
    }
}
