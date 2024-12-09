package ru.job4j.service.film;

import ru.job4j.dto.FilmDto;
import ru.job4j.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {
    Optional<FilmDto> findById(int id);
    Collection<FilmDto> findAll();
}
