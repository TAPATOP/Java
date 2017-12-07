package bg.uni.sofia.fmi.mjt.cinema.reservation.system;

import bg.uni.sofia.fmi.mjt.cinema.reservation.system.core.*;
import bg.uni.sofia.fmi.mjt.cinema.reservation.system.exceptions.*;
import javafx.beans.binding.SetBinding;
import javafx.collections.ObservableSet;

import java.time.LocalDateTime;
import java.util.*;

public class CinemaCity implements CinemaReservationSystem {

    // Constructors //
    public CinemaCity(Map<Movie, List<Projection>> cinemaProgram) {
        this.cinemaProgram = cinemaProgram;
        tickets = new HashMap<>();

        for(Map.Entry<Movie, List<Projection>> map : cinemaProgram.entrySet()) {
            List<Projection> list = map.getValue();

            for (Projection proj :
                    list) {
                tickets.put(proj, new LinkedList<Ticket>());
            }
        }
    }

    // Interface stuff //
    @Override
    public void bookTicket(Ticket ticket) throws AlreadyReservedException, ProjectionNotFoundException, InvalidSeatException, ExpiredProjectionException {
        Projection ticketProj = ticket.getProjection();
        Movie ticketMov = ticketProj.getMovie();
        Hall ticketHall = ticketProj.getHall();

        if(ticketProj.getDate().compareTo(LocalDateTime.now()) < 0){
            throw new ExpiredProjectionException();
        }

        if(!cinemaProgram.containsKey(ticketMov) || cinemaProgram.get(ticketMov).indexOf(ticketProj) == -1){
            throw new ProjectionNotFoundException();
        }

        if(
                ticketHall.getRowSeats() < ticket.getSeat().getSeat() ||
                ticketHall.getRowSeats() < 1 ||
                ticketHall.getRows() < ticket.getSeat().getRow() ||
                ticketHall.getRows() < 1){

            throw new InvalidSeatException();
        }

        for (Ticket tick :
                tickets.get(ticketProj)) {
            if (tick.equals(ticket)) {
                throw new AlreadyReservedException();
            }
        }

        tickets.get(ticketProj).add(ticket);
    }

    @Override
    public void cancelTicket(Ticket ticket) throws ReservationNotFoundException, ProjectionNotFoundException {
        Projection ticketProj = ticket.getProjection();
        Movie ticketMov = ticketProj.getMovie();

        if(!tickets.containsKey(ticketProj)){
            throw new ProjectionNotFoundException();
        }

        List<Ticket> list = tickets.get(ticketProj);
        for (Ticket tic :
                list) {
            if (tic.equals(ticket)){
                list.remove(ticket);
                return;
            }
        }

        throw new ReservationNotFoundException();
    }

    @Override
    public int getFreeSeats(Projection projection) throws ProjectionNotFoundException {
        if(!tickets.containsKey(projection)){
            throw new ProjectionNotFoundException();
        }

        List<Ticket> list = tickets.get(projection);
        int counter = 0;
        for (Ticket tic :
                list) {
            counter++;
        }

        return (projection.getHall().getRows() * projection.getHall().getRowSeats()) - counter;
    }

    @Override
    public Collection<Movie> getSortedMoviesByGenre() {
        // Could be done with some Map transformations n stuff but i dont have the time

        Set<Movie> set = new HashSet<>();
        LinkedList<Movie> output = new LinkedList<>();

        for(Map.Entry<Movie, List<Projection>> map : cinemaProgram.entrySet()) {
            set.add(map.getKey());
        }
        sorter(set, output, MovieGenre.ACTION);
        sorter(set, output, MovieGenre.ADVENTURE);
        sorter(set, output, MovieGenre.COMEDY);
        sorter(set, output, MovieGenre.DRAMA);
        sorter(set, output, MovieGenre.FANTASY);
        sorter(set, output, MovieGenre.HORROR);
        sorter(set, output, MovieGenre.THRILLER);

        return output;
    }

    // Private //
    private void sorter(Set<Movie> source, List<Movie> output, MovieGenre genre){
        for (Movie mov :
                source) {
            if(mov.getGenre() == genre){
                output.add(mov);
            }
        }
    }
    // Member variables //
    private Map<Movie, List<Projection>> cinemaProgram;
    private Map<Projection, LinkedList<Ticket>> tickets;
}
