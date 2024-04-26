package repositories;

import models.Movie;

import java.util.*;

public class MovieRepo {
    private List<Movie> movieList;

    public MovieRepo() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        this.movieList.add(movie);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public Movie getMovieById(UUID id) {
        for (Movie movie: movieList) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }

        return null;
    }

    public List<Movie> searchByTitle(String title) {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getTitle().contains(title)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> searchByCategory(String category) {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getCategory().equals(category)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> searchByCast(String cast) {
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : movieList) {
            for (String currCast : movie.getCast()) {
                if (currCast.equals(cast)) {
                    movies.add(movie);
                }
            }

        }

        movies.sort(Comparator.comparing(Movie::getTitle));
        return movies;
    }
}
