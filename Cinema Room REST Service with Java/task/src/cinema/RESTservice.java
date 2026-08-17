package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RESTservice {

    Cinema seats = new Cinema(9, 9);

    @GetMapping("/seats")
    public ResponseEntity<?> getAllSeats() {

        return ResponseEntity
                .ok(seats);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> makePurchase(@RequestBody Seats seatsToPurchase){

        if (!seats.isCinemaSeat(seatsToPurchase.getRow(), seatsToPurchase.getColumn())){
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        Seats requestedSeat = seats.canPurchaseCinemaSeat(seatsToPurchase.getRow(), seatsToPurchase.getColumn());
        if (requestedSeat != null){
            return ResponseEntity
                    .ok(requestedSeat);
        }

        throw new SeatNotFoundException("The ticket has been already purchased!");

    }

    // required for formatting of message which uses internal quotation marks
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<?> handleSeatNotFound(SeatNotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("error", ex.getMessage()));
    }

}

