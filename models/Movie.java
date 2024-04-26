package models;

import java.util.List;
import java.util.UUID;

public class Movie {
    private final UUID id;
    private String title;
    private String releaseYear;
    private String category;
    private int budget;
    private List<String> cast;

    public Movie(String title, String releaseYear, String category, int budget, List<String> cast) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.releaseYear = releaseYear;
        this.category = category;
        this.budget = budget;
        this.cast = cast;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getCategory() {
        return category;
    }

    public int getBudget() {
        return budget;
    }

    public List<String> getCast() {
        return cast;
    }

    @Override
    public String toString() {
        return
                "title: " + title + '\n' +
                "releaseYear: " + releaseYear + '\n' +
                "category: " + category + '\n' +
                "budget: " + budget + '\n' +
                "cast: " + cast + "\n\n";
    }
}
