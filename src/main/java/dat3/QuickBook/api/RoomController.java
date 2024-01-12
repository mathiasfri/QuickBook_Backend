package dat3.QuickBook.api;

import dat3.QuickBook.dto.RoomRequest;
import dat3.QuickBook.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PutMapping("/create/{id}")
    public void createRoom(@PathVariable Long id, @RequestBody RoomRequest body){
        roomService.createRoom(id, body);
    }
}
