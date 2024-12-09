package ru.job4j.repository.film;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.model.Film;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmRepository implements FilmRepository {
    private final Sql2o sql2o;

    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Film> findById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM films WHERE id = :id")
                    .addParameter("id", id);
            var film = query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetchFirst(Film.class);
            return Optional.ofNullable(film);
        }
    }

    @Override
    public Optional<Film> findByName(String name) {
       try (Connection con = sql2o.open()) {
           var query = con.createQuery("SELECT * FROM films WHERE name = :name")
                   .addParameter("name", name);
           var film = query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetchFirst(Film.class);
           return Optional.ofNullable(film);
       }
    }

    @Override
    public Collection<Film> findAll() {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM films");
            return query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetch(Film.class);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("DELETE FROM films WHERE id = :id")
                    .addParameter("id", id);
           return query.executeUpdate().getResult() > 0;
        }
    }
}
