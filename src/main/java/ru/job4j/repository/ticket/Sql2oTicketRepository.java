package ru.job4j.repository.ticket;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.model.Ticket;

import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("""
                    INSERT INTO tickets 
                        (session_id, row_number, place_number, user_id) 
                    VALUES 
                        (:sessionId, :rowNumber, :placeNumber, :userId)""");
            query.addParameter("sessionId", ticket.getSessionId());
            query.addParameter("rowNumber", ticket.getRowNumber());
            query.addParameter("placeNumber", ticket.getPlaceNumber());
            query.addParameter("userId", ticket.getUserId());
            int tickets = query.executeUpdate().getKey(Integer.class);
            ticket.setId(tickets);
            return Optional.of(ticket);
        }
    }

    @Override
    public Optional<Ticket> findById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM tickets WHERE id = :id");
            query.addParameter("id", id);
            var tickets = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(tickets);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("DELETE FROM tickets WHERE id = :id")
                    .addParameter("id", id);
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeUpdate().getResult() > 0;
        }
    }

    @Override
    public boolean update(Ticket ticket) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("""
                                        UPDATE tickets
                                        SET session_id = :sessionId, row_number = :rowNumber, place_number = :placeNumber, user_id = :userId
                    WHERE id = :id");"
                                        """);
            query.addParameter("id", ticket.getId());
            query.addParameter("sessionId", ticket.getSessionId());
            query.addParameter("rowNumber", ticket.getRowNumber());
            query.addParameter("placeNumber", ticket.getPlaceNumber());
            query.addParameter("userId", ticket.getUserId());
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeUpdate().getResult() > 0;
        }
    }
}