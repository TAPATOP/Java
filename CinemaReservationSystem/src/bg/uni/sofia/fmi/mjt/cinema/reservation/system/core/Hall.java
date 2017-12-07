package bg.uni.sofia.fmi.mjt.cinema.reservation.system.core;

public class Hall {

    // Constructors //
    public Hall(int number, int rows, int rowSeats) {
        this.number = number;
        this.rows = rows;
        this.rowSeats = rowSeats;
    }

    // Getters //
    public int getRows() {
        return rows;
    }

    public int getRowSeats() {
        return rowSeats;
    }

    // Member variables //
    private int number;
    private int rows;
    private int rowSeats;
}
