package ru.job4j.service.users;

import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.users.Sql2oUserRepository;

import java.util.Collection;;
import java.util.Optional;

@Service
public class SimpleUserService implements UserService {
    private final Sql2oUserRepository sql2oUserRepository;
    public SimpleUserService(Sql2oUserRepository sql2oUserRepository) {
        this.sql2oUserRepository = sql2oUserRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return sql2oUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return sql2oUserRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean deleteByEmail(String email) {
        return sql2oUserRepository.deleteByEmail(email);
    }

    @Override
    public Collection<User> findAll() {
        return sql2oUserRepository.findAll();
    }
}
