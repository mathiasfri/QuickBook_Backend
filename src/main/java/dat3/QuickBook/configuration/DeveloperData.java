package dat3.QuickBook.configuration;

import dat3.QuickBook.entity.Hotel;
import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.HotelRepository;
import dat3.QuickBook.repository.RoomRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class DeveloperData implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    public DeveloperData(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
        generateTestData();
    }

    private void generateTestData() {
        List<Hotel> hotels = new ArrayList<>();

        for (int i = 0; i < 250; i++) {
            Hotel hotel = new Hotel();
            hotel.setName("Hotel" + (i + 1));
            hotel.setStreet("Street " + (i + 1));
            hotel.setCity("City " + (i + 1));
            hotel.setZip("Zip " + (i + 1));
            hotel.setCountry("Country " + (i + 1));

            hotelRepository.save(hotel);

            List<Room> rooms = generateRooms(hotel);
            hotel.setRooms(rooms);


            hotels.add(hotel);
        }

        hotelRepository.saveAll(hotels);
    }

    private List<Room> generateRooms(Hotel hotel) {
        List<Room> rooms = new ArrayList<>();

        int numberOfRooms = ThreadLocalRandom.current().nextInt(10, 41);
        int roomNumberOffset = rooms.size(); // Ensure unique room numbers for each hotel

        for (int i = 1; i <= numberOfRooms; i++) {
            Room room = new Room();
            room.setRoomNumber(roomNumberOffset + i);
            room.setNumberOfBeds(ThreadLocalRandom.current().nextInt(1, 5));
            room.setHotel(hotel);

            // List<Reservation> reservations = generateReservations(room);
            // room.setReservations(reservations);

            room.setCreated(LocalDateTime.now());
            room.setUpdated(LocalDateTime.now());

            roomRepository.save(room); // Save each room to the repository

            rooms.add(room);
        }

        return rooms;
    }



    private void setupUserWithRoleUsers() {
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}
