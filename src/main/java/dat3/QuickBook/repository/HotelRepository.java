package dat3.QuickBook.repository;

import dat3.QuickBook.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    Hotel findById(Long id);
}
