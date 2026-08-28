package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

public class Cinema {

    private int rows;
    private int columns;
    private int price = 10;
    private LinkedList<Seats> seats = new LinkedList<>();


    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        int i = 0;
        for (int r = 0; r < rows; r++) {
            if (r > 3) {
                price = 8;
            }
            for (int c = 0; c < columns; c++) {
                seats.add(new Seats(r + 1, c + 1, price, i));
                i++;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @JsonProperty("seats") //incoming JSON property is "seats", but I named it Cinema, so
    //I renamed it by using @Json Property
    public LinkedList<Seats> getCinema() {
        return seats;
    }

    public Seats isAvailableSeat(int requestRow, int requestedColumn) {
        for (Seats s : seats) {
            if (s.getRow() == requestRow && s.getColumn() == requestedColumn) {
                seats.remove(s);
                return s;
            }
        }

        return null;
    }


    public boolean isCinemaSeat(int requestRow, int requestColumn) {
        return requestRow >= 1 && requestRow <= rows
                && requestColumn >= 1 && requestColumn <= columns;
    }

    public void restoreSeatToCinema(Seats refundedSeat) {
        if (!isCinemaSeat(refundedSeat.getRow(), refundedSeat.getColumn())) {
            return;
        }
        seats.add(refundedSeat.getIndex(), refundedSeat);
    }

}

