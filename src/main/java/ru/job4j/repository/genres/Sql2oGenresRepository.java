package ru.job4j.repository.genres;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.model.Genres;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class Sql2oGenresRepository implements GenresRepository {
    private final Sql2o sql2o;

    public Sql2oGenresRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<Genres> findAll() {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM genres");
            return query.setColumnMappings(Genres.COLUMN_MAPPING).executeAndFetch(Genres.class);
        }
    }

    @Override
    public Optional<Genres> findById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM genres WHERE id = :id")
                    .addParameter("id", id);
            var genres = query.setColumnMappings(Genres.COLUMN_MAPPING).executeAndFetchFirst(Genres.class);
            return Optional.ofNullable(genres);
        }
    }
}
