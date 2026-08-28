package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seats {

    private int row;
    private int column;
    private int price;

    private int index;

    public Seats() {
    }

    public Seats(int row, int column, int price, int index) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.index = index;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
