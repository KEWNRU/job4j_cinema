package ru.job4j.service.ticket;

import org.springframework.stereotype.Service;
import ru.job4j.model.Ticket;
import ru.job4j.repository.ticket.Sql2oTicketRepository;

import java.util.Optional;

@Service
public class SimpleTicketService implements TicketService {
    private final Sql2oTicketRepository sql2oTicketRepository;

    public SimpleTicketService(final Sql2oTicketRepository sql2oTicketRepository) {
        this.sql2oTicketRepository = sql2oTicketRepository;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return sql2oTicketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return sql2oTicketRepository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return sql2oTicketRepository.deleteById(id);
    }

    @Override
    public boolean update(Ticket ticket) {
        return sql2oTicketRepository.update(ticket);
    }
}
