package dat3.QuickBook.service;

import dat3.QuickBook.dto.HotelRequest;
import dat3.QuickBook.dto.ReservationRequest;
import dat3.QuickBook.dto.ReservationResponse;
import dat3.QuickBook.entity.Reservation;
import dat3.QuickBook.entity.Room;
import dat3.QuickBook.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse createReservation(ReservationRequest body){
        if (isRoomAvailable(body.getRoom(), body.getReservationDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room not available on the specified date");
        }

        Reservation reservation = new Reservation();
        reservation.setGuest(body.getGuest());
        reservation.setRoom(body.getRoom());
        reservation.setReservationDate(body.getReservationDate());
        reservation.setCreated(LocalDateTime.now());
        reservation.setUpdated(LocalDateTime.now());

        return new ReservationResponse(reservationRepository.save(reservation));
    }

    public boolean isRoomAvailable(Room room, LocalDateTime reservationDate){
        List<Reservation> reservations = reservationRepository.findByIdAndReservationDate(room.getId(), reservationDate);

        return !reservations.isEmpty();
    }

    public void cancelReservation(int id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        reservationRepository.delete(reservation);
    }
}
