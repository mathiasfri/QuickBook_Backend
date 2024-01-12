package dat3.QuickBook.api;

import dat3.QuickBook.dto.GuestRequest;
import dat3.QuickBook.service.GuestService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PutMapping("/create")
    public void createGuest(@RequestBody GuestRequest body){
        guestService.createGuest(body);
    }
}
