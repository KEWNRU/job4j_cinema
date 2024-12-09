package ru.job4j.repository.filmsession;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.model.FilmSessions;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {
    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<FilmSessions> findById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM film_sessions WHERE id = :id")
                    .addParameter("id", id);
            var film = query.setColumnMappings(FilmSessions.COLUMN_MAPPING).executeAndFetchFirst(FilmSessions.class);
            return Optional.of(film);
        }
    }

    @Override
    public Collection<FilmSessions> findAll() {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSessions.COLUMN_MAPPING).executeAndFetch(FilmSessions.class);
        }
    }
}
