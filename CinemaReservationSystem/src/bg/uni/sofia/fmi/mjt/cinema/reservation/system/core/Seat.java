package bg.uni.sofia.fmi.mjt.cinema.reservation.system.core;

public class Seat {

    // Constructors //
    public Seat(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    // Getters //
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    // Member variables //
    private int row;
    private int seat;
}
