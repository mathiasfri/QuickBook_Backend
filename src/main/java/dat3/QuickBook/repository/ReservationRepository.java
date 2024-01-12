package dat3.QuickBook.repository;

import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByIdAndReservationDate(int id, LocalDateTime reservationDate);
}
