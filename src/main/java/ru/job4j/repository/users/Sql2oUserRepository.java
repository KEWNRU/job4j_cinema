package ru.job4j.repository.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.model.User;
import ru.job4j.repository.ticket.Sql2oTicketRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {
    private static final Logger LOG = LogManager.getLogger(Sql2oUserRepository.class.getName());
    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try (Connection connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO users (full_name, password, email) VALUES (:full_name, :password, :email)");
            query.addParameter("full_name", user.getFullName());
            query.addParameter("password", user.getPassword());
            query.addParameter("email", user.getEmail());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Sql2oException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users WHERE email = :email and password = :password");
            query.addParameter("email", email);
            query.addParameter("password", password);
            var user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        try (Connection connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM users WHERE email = :email");
            query.addParameter("email", email);
            return query.executeUpdate().getResult() > 0;
        }
    }

    @Override
    public Collection<User> findAll() {
        try (Connection connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users");
            return query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetch(User.class);
        }
    }
}
