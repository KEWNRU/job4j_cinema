package ru.job4j.service.filmsession;

import ru.job4j.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionService {
    Optional<FilmSessionDto> findById(int id);
    Collection<FilmSessionDto> findAll();
}
