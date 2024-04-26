package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favouriteMovies;

    public User(String email) {
        this.email = email;
        this.favouriteMovies = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void addFavouriteMovie(Movie movie) {
        this.favouriteMovies.add(movie);
    }

    public void removeFavouriteMovie(Movie movie) {
        this.favouriteMovies.remove(movie);
    }
}
