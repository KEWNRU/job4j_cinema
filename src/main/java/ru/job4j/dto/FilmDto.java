package ru.job4j.dto;


import java.util.Objects;

public class FilmDto {

    private int id;
    private String name;
    private String description;
    private int year;
    private int minimalAge;
    private int durationInMinutes;
    private String genre;
    private int fileId;

    public FilmDto(int id, String genre, int durationInMinutes, int minimalAge, int year, String description, String name, int fileId) {
        this.id = id;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.minimalAge = minimalAge;
        this.year = year;
        this.description = description;
        this.name = name;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilmDto filmDto)) {
            return false;
        }
        return getId() == filmDto.getId() && getYear() == filmDto.getYear() && getMinimalAge() == filmDto.getMinimalAge() && getDurationInMinutes() == filmDto.getDurationInMinutes() && Objects.equals(getName(), filmDto.getName()) && Objects.equals(getDescription(), filmDto.getDescription()) && Objects.equals(getGenre(), filmDto.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getYear(), getMinimalAge(), getDurationInMinutes(), getGenre());
    }
}
