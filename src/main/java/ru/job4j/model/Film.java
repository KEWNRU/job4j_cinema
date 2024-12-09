package ru.job4j.model;

import java.util.Map;
import java.util.Objects;

public class Film {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "description", "description",
            "name", "name",
            "year", "year",
            "genre_id", "genre",
            "minimal_age", "minimalAge",
            "duration_in_minutes", "durationInMinutes",
            "file_id", "fileId"
    );


    private int id;
    private String name;
    private String description;
    private int year;
    private int minimalAge;
    private int durationInMinutes;
    private String genre;
    private int fileId;

    public Film(int id, String name, int year, String description, int minimalAge, String genre, int durationInMinutes, int fileId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.minimalAge = minimalAge;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film film)) {
            return false;
        }
        return getId() == film.getId()
                && getYear() == film.getYear()
                && getMinimalAge() == film.getMinimalAge()
                && getDurationInMinutes() == film.getDurationInMinutes()
                && Objects.equals(getName(), film.getName())
                && Objects.equals(getDescription(), film.getDescription())
                && Objects.equals(getGenre(), film.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getYear(), getMinimalAge(), getDurationInMinutes(), getGenre());
    }
}
