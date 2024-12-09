package ru.job4j.repository.film;

import ru.job4j.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository {
    Optional<Film> findById(int id);
    Optional<Film> findByName(String name);
    Collection<Film> findAll();
    boolean deleteById(int id);
}
