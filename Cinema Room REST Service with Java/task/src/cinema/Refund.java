package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Refund {
    @JsonIgnore
    Purchase purchase;

    Seats ticket;

    public Refund() {
    }

    public Refund(Purchase purchase, Seats ticket) {
        this.ticket = ticket;
        this.purchase = purchase;
    }

    public void refundTicketPurchase(UUID token) {
        purchase.getPurchasedSeatMap().remove(token);
    }

    public Seats getTicket() {
        return ticket;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }


}
