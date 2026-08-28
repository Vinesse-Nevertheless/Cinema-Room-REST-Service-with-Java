package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Purchase {

    private UUID token;
    private Seats ticket;

    private Map<UUID, Seats> purchasedSeatMap = new HashMap<>();

    public Purchase() {
    }

    public Purchase(Seats ticket) {
        this.ticket = ticket;
        this.token = UUID.randomUUID();
        purchasedSeatMap.put(this.token, this.ticket);

    }

    public Seats getPurchasedSeat(UUID token) {
        if (purchasedSeatMap.containsKey(token)) {
            return purchasedSeatMap.get(token);
        }
        return null;
    }

    @JsonIgnore
    public Map<UUID, Seats> getPurchasedSeatMap() {
        return purchasedSeatMap;
    }

    public void setPurchasedSeatMap(Map<UUID, Seats> purchasedSeatMap) {
        this.purchasedSeatMap = purchasedSeatMap;
    }

    public Seats getTicket() {
        return ticket;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

}
