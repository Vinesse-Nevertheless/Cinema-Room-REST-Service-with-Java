package cinema;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class RESTservice {

    Cinema seats = new Cinema(9, 9);
    Purchase purchase;
    Refund refund;
    Statistics statistics;

    //for password stored in applications.properties file
    @Value("${cinema.secret-password}")
    private String adminPassword;

    @GetMapping("/seats")
    public ResponseEntity<?> getAllSeats() {

        return ResponseEntity
                .ok(seats);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> makePurchase(@RequestBody Seats seatsToPurchase) {

        if (!seats.isCinemaSeat(seatsToPurchase.getRow(), seatsToPurchase.getColumn())) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        Seats requestedSeat = seats.isAvailableSeat(seatsToPurchase.getRow(), seatsToPurchase.getColumn());
        if (requestedSeat != null) {
            purchase = new Purchase(requestedSeat);
            return ResponseEntity
                    .ok(purchase);
        }

        throw new SeatNotFoundException("The ticket has been already purchased!");

    }

    @PostMapping("/return")
    //return using UUID token
    public ResponseEntity<?> refundTicket( @RequestBody Purchase refundPurchase) {
        UUID token = refundPurchase.getToken();
        Seats refundableSeat = purchase.getPurchasedSeat(token);

        if (refundableSeat != null) {
            seats.restoreSeatToCinema(refundableSeat);
            refund = new Refund(purchase, refundableSeat);
            refund.refundTicketPurchase(token);

            return ResponseEntity.ok(refund);
        }
        throw new SeatNotFoundException("Wrong token!");
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStatistics(@RequestParam (required = false) String password) {
        if (password != null && password.equals(adminPassword)) {
            int income = Statistics.calculateIncome(purchase);
            int available = Statistics.calculateAvailability(seats);
            int purchased = Statistics.calculateNumPurchased(purchase);
            statistics = new Statistics(income, available, purchased);
            return ResponseEntity
                    .ok(statistics);
        }

        throw new IncorrectPasswordException("The password is wrong!");
    }


    // required for formatting of message which uses internal quotation marks
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<?> handleSeatNotFound(SeatNotFoundException ex) {
        return ResponseEntity
                .badRequest()  //404
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<?> handleIncorrectPassword(IncorrectPasswordException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) //401
                .body(Map.of("error", e.getMessage()));
    }
}



