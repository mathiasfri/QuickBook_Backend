package dat3.QuickBook.api;

import dat3.QuickBook.dto.HotelRequest;
import dat3.QuickBook.dto.HotelResponse;
import dat3.QuickBook.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @PutMapping("/create")
    public HotelResponse createHotel(@RequestBody HotelRequest body){
        return hotelService.createHotel(body);
    }

    @GetMapping
    public List<HotelResponse> getHotels() {
        return hotelService.getAllHotels(false);
    }

    @GetMapping("/{id}")
    public HotelResponse getHotelById(@PathVariable Long id){
        return hotelService.getHotelByName(id, false);
    }

    @PutMapping("/update/{id}")
    public HotelResponse updateHotel(@RequestBody HotelRequest body, @PathVariable Long id){
        return hotelService.updateHotel(body, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }
}
