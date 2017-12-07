package bg.uni.sofia.fmi.mjt.cinema.reservation.system.core;

public class Movie {

    // Constructors //
    public Movie(String name, int duration, MovieGenre genre) {
        this.name = name;
        this.duration = duration;
        this.genre = genre;
    }
    // Getters //
    public MovieGenre getGenre() {
        return genre;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (duration != movie.duration) return false;
        if (!name.equals(movie.name)) return false;
        return genre == movie.genre;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + duration;
        result = 31 * result + genre.hashCode();
        return result;
    }

    public String getName(){
        return name;
    }
    // Member variables //
    private String name;
    private int duration;
    private MovieGenre genre;
}
