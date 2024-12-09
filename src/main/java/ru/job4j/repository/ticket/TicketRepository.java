package ru.job4j.repository.ticket;

import ru.job4j.model.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket);
    Optional<Ticket> findById(int id);
    boolean deleteById(int id);
    boolean update(Ticket ticket);
}
