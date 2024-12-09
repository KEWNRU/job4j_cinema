package ru.job4j.repository.filmsession;

import ru.job4j.model.FilmSessions;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {
    Optional<FilmSessions> findById(int id);
    Collection<FilmSessions> findAll();
}
