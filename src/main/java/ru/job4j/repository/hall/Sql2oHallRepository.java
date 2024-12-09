package ru.job4j.repository.hall;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.model.Halls;

import java.util.Optional;

@Repository
public class Sql2oHallRepository implements HallRepository {
    private final Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public Optional<Halls> findById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM halls WHERE id = :id").addParameter("id", id);
            query.addParameter("id", id);
            var hall = query.setColumnMappings(Halls.COLUMN_MAPPING).executeAndFetchFirst(Halls.class);
            return Optional.of(hall);
        }
    }

    @Override
    public Optional<Halls> findByName(String name) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM halls WHERE name = :name").addParameter("name", name);
            query.addParameter("name", name);
            var hall = query.setColumnMappings(Halls.COLUMN_MAPPING).executeAndFetchFirst(Halls.class);
            return Optional.of(hall);
        }
    }
}
