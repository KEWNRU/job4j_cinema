package ru.job4j.repository.genres;

import ru.job4j.model.Genres;

import java.util.Collection;
import java.util.Optional;

public interface GenresRepository {
    Optional<Genres> findById(int id);
    Collection<Genres> findAll();
}
