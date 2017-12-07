package bg.uni.sofia.fmi.mjt.cinema.reservation.system.core;

public class Ticket {

    // Constuctors //
    public Ticket(Projection projection, Seat seat, String owner) {
        this.projection = projection;
        this.seat = seat;
        this.owner = owner;
    }

    // Getters //
    public Projection getProjection() {
        return projection;
    }

    public Seat getSeat() {
        return seat;
    }

    // equals() and hashCode() //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!projection.equals(ticket.projection)) return false;
        return seat.equals(ticket.seat);
    }

    @Override
    public int hashCode() {
        int result = projection.hashCode();
        result = 31 * result + seat.hashCode();
        return result;
    }

    // Member variables //
    private Projection projection;
    private Seat seat;
    private String owner;
}
