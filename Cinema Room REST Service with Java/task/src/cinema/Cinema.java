package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int rows;
    private int columns;
    private List<Seats> seats;


    public Cinema(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        seats = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                seats.add(new Seats(r+1, c+1));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @JsonProperty("seats")
    public List<Seats> getCinema() {
        return seats;
    }
}

