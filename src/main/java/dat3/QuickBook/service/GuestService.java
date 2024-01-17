package dat3.QuickBook.service;

import dat3.QuickBook.dto.GuestRequest;
import dat3.QuickBook.dto.GuestResponse;
import dat3.QuickBook.entity.Guest;
import dat3.QuickBook.repository.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestResponse createGuest(GuestRequest body){
        Guest guest = new Guest();
        guest.setUsername(body.getUsername());
        guest.setPassword(body.getPassword());
        guest.setFirstName(body.getFirstName());
        guest.setLastName(body.getLastName());
        guest.setEmail(body.getEmail());
        guest.setPhoneNumber(body.getPhoneNumber());
        guest.setCreated(body.getCreated());
        guest.setUpdated(body.getUpdated());
        return new GuestResponse(guestRepository.save(guest));
    }
}
