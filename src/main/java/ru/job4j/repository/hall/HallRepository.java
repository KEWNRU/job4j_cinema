package ru.job4j.repository.hall;

import ru.job4j.model.Halls;

import java.util.Optional;

public interface HallRepository {
    Optional<Halls> findById(int id);
    Optional<Halls> findByName(String name);
}
