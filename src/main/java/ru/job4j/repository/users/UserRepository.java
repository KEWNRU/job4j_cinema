package ru.job4j.repository.users;

import java.util.Collection;
import java.util.Optional;
import ru.job4j.model.User;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean deleteByEmail(String email);
    Collection<User> findAll();
}
