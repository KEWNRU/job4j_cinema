package ru.job4j.repository.ticket;

import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import ru.job4j.model.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket) throws DuplicateKeyException;
    Optional<Ticket> findById(int id);
    boolean deleteById(int id);
    boolean update(Ticket ticket);
}
