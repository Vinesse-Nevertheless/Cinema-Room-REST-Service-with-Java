package cinema;

public class Seats {

    private int row;
    private int column;

    public Seats(int row, int col){
        this.row = row;
        this.column = col;
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

    public void setColumn(int col) {
        this.column = column;
    }

}
