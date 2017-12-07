package bg.uni.sofia.fmi.mjt.cinema.reservation.system;

import bg.uni.sofia.fmi.mjt.cinema.reservation.system.core.*;

import java.time.LocalDateTime;
import java.util.*;

public class Client {
    public static void main(String args[]){

        Hall hall1 = new  Hall(1, 10, 10);
        Hall hall2 = new Hall(2, 20, 10);

        Movie mov1 = new Movie("Triumph des Willens", 105, MovieGenre.DRAMA);
        Movie mov2 = new Movie("New Kids Turbo", 120, MovieGenre.COMEDY);
        Movie mov3 = new Movie("Flatliners", 115, MovieGenre.HORROR);

        LocalDateTime time1 = LocalDateTime.of(2017, 4, 20, 14, 48);
        LocalDateTime time2 = LocalDateTime.of(2017, 12, 12, 14, 00);
        LocalDateTime time3 = LocalDateTime.of(2018, 4, 1, 19, 45);
        LocalDateTime time4 = LocalDateTime.of(2118, 4, 1, 19, 45);

        Projection proj1 = new Projection(mov1, hall2, time1);
        Projection proj2 = new Projection(mov1, hall2, time2);
        Projection proj3 = new Projection(mov1, hall1, time2);
        Projection proj4 = new Projection(mov2, hall1, time3);
        Projection proj5 = new Projection(mov2, hall2, time3);

        Projection proj6 = new Projection(mov2, hall2, time4);

        Seat seat1 = new Seat(20, 10);
        Seat seat2 = new Seat(15, 8);
        Seat seat3 = new Seat(1, 1);
        Seat seat4 = new Seat(1, 2);

        // Projection Lists
        List mov1List = new LinkedList();
        mov1List.add(proj1);
        mov1List.add(proj2);
        mov1List.add(proj3);

        List mov2List = new LinkedList();
        mov2List.add(proj4);
        mov2List.add(proj5);

        mov2List.add(proj6);

        // Map definition goes here //
        Map theMap = new HashMap();
        theMap.put(mov1, mov1List);
        theMap.put(mov2, mov2List);

        // TODO: test with repeating seat
        Ticket tic1 = new Ticket(proj2, seat1, "Itsko");
        Ticket tic2 = new Ticket(proj2, seat2, "Itsko 2");
        Ticket tic3 = new Ticket(proj2, seat3, "Itsko evil twin");

        Ticket tic4 = new Ticket(proj6, seat4, "Itswko");

        // Testing starts here //
        CinemaCity CCity = new CinemaCity(theMap);

        CCity.bookTicket(tic1);
        CCity.bookTicket(tic2);
        CCity.bookTicket(tic3);
        CCity.bookTicket(tic4);

        System.out.println(CCity.getFreeSeats(proj2));

        CCity.getSortedMoviesByGenre();

    }
}
