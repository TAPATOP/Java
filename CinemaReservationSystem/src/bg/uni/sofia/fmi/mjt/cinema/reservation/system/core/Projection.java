package bg.uni.sofia.fmi.mjt.cinema.reservation.system.core;

import java.time.LocalDateTime;
import java.util.LinkedList;

import bg.uni.sofia.fmi.mjt.cinema.reservation.system.exceptions.*;

public class Projection {

    // Constructors //
    public Projection(Movie movie, Hall hall, LocalDateTime date) {
        this.movie = movie;
        this.hall = hall;
        this.date = date;
    }

    // Getters //
    public LocalDateTime getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }

    // equals() and hashCode() //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projection that = (Projection) o;

        if (!movie.equals(that.movie)) return false;
        if (!hall.equals(that.hall)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = movie.hashCode();
        result = 31 * result + hall.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    // Stuff //
    public Movie getMovie() {
        return movie;
    }

    // Member variables //
    private Movie movie;
    private Hall hall;
    private LocalDateTime date;
}
