import models.Movie;
import models.User;
import repositories.MovieRepo;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static void printMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }
        int index = 1;
        for (Movie movie : movies) {
            System.out.println(index++ + ": " + movie.getTitle());
        }
    }

    private static void showMovieDetails(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Year: " + movie.getReleaseYear());
        System.out.println("Category: " + movie.getCategory());
        System.out.println("Cast: " + String.join(", ", movie.getCast()));
    }

    private static void handleMovieSelection(MovieRepo movieRepo, User user, List<Movie> movies) {
        System.out.println("\nEnter the index of the movie to see details, or 0 to cancel:");
        int index = scanner.nextInt() - 1;

        if (index == -1) {
            return;
        }

        if (index < 0 || index >= movies.size()) {
            System.out.println("Invalid movie index.");
            return;
        }

        Movie selectedMovie = movies.get(index);
        showMovieDetails(selectedMovie);

        if (user.getFavouriteMovies().contains(selectedMovie)) {
            System.out.println("2. Remove movie from favorites.");
        } else {
            System.out.println("\n1. Add movie to favorites.");
        }
        System.out.println("3. Return to main menu.");
        System.out.println("0. Exit the application.");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                user.addFavouriteMovie(selectedMovie);
                System.out.println("Added to favorites.");
                break;
            case 2:
                user.removeFavouriteMovie(selectedMovie);
                System.out.println("Removed from favorites.");
                break;
            case 3:
                break;
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void populateMovies(MovieRepo movieRepo) {
        movieRepo.addMovie(new Movie("Dune", "2020", "Sci-Fi", 100, List.of("abc", "def", "xyz")));
        movieRepo.addMovie(new Movie("Dune 2", "2024", "Sci-Fi", 100, List.of("abc", "def", "xyz")));
        movieRepo.addMovie(new Movie("Top Gun", "2023", "War", 100, List.of("abc", "mno", "xyz")));
        movieRepo.addMovie(new Movie("Mission Impossible", "2030", "Horror", 100, List.of("pqr", "mno", "xyz")));
        movieRepo.addMovie(new Movie("John Wick", "2023", "Action", 100, List.of("pqr", "mno", "xyz")));
        movieRepo.addMovie(new Movie("John Wick 2", "2024", "Action", 100, List.of("pqr", "mno", "xyz")));
        movieRepo.addMovie(new Movie("Jerry Maqguire", "2024", "Classic", 100, List.of("pqr", "mno", "xyz")));
    }

    public static void main(String[] args) {
        MovieRepo movieRepo = new MovieRepo();
        populateMovies(movieRepo); // Suppose this method adds some predefined movies
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(email);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1 - Show all movies.");
            System.out.println("2 - Search a movie by title.");
            System.out.println("3 - Search a movie by category.");
            System.out.println("4 - Search a movie by cast.");
            System.out.println("5 - Show all favorite movies.");
            System.out.println("0 - Exit.");

            int option = scanner.nextInt();
            scanner.nextLine();
            List<Movie> filterdMovies;

            switch (option) {
                case 1:
                    filterdMovies = movieRepo.getMovieList();
                    printMovies(filterdMovies);
                    handleMovieSelection(movieRepo, user, filterdMovies);
                    break;
                case 2:
                    System.out.println("Enter the title: ");
                    String title = scanner.nextLine();
                    filterdMovies = movieRepo.searchByTitle(title);
                    printMovies(filterdMovies);
                    handleMovieSelection(movieRepo, user, filterdMovies);
                    break;
                case 3:
                    System.out.println("Enter the category: ");
                    String category = scanner.nextLine();
                    filterdMovies = movieRepo.searchByCategory(category);
                    printMovies(filterdMovies);
                    handleMovieSelection(movieRepo, user, filterdMovies);
                    break;
                case 4:
                    System.out.println("Enter the cast: ");
                    String cast = scanner.nextLine();
                    filterdMovies = movieRepo.searchByCast(cast);
                    printMovies(filterdMovies);
                    handleMovieSelection(movieRepo, user, filterdMovies);
                    break;
                case 5:
                    filterdMovies = user.getFavouriteMovies();
                    printMovies(filterdMovies);
                    handleMovieSelection(movieRepo, user, filterdMovies);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Select a valid option...");
            }
        }
    }
}
