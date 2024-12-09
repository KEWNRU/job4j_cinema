package ru.job4j.dto;

import java.time.LocalDateTime;

public class FilmSessionDto {
    private int id;
    private String filmName;
    private String hallsName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;

    public FilmSessionDto(int id, String filmName, String hallsName, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.filmName = filmName;
        this.hallsName = hallsName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }


    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getHallsName() {
        return hallsName;
    }

    public void setHallsName(String hallsId) {
        this.hallsName = hallsId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
