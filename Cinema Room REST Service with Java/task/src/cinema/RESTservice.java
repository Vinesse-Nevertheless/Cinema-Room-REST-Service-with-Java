package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTservice {

    @GetMapping("/seats")

    public ResponseEntity<?> getAllSeats() {
        Cinema seats = new Cinema(9, 9);
        return ResponseEntity
                .ok(seats);
    }
}

