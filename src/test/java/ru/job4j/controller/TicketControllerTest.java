package ru.job4j.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.dto.FilmSessionDto;
import ru.job4j.dto.HallsDto;
import ru.job4j.model.Ticket;
import ru.job4j.service.filmsession.FilmSessionService;
import ru.job4j.service.hall.HallService;
import ru.job4j.service.ticket.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {

    private TicketController controller;
    private FilmSessionService service;
    private HallService hallService;
    private TicketService ticketService;

    @BeforeEach
    public void initServices() {
        service = mock(FilmSessionService.class);
        hallService = mock(HallService.class);
        ticketService = mock(TicketService.class);
        controller = new TicketController(ticketService, service, hallService);
    }

    @Test
    void whenFilmSessionThenGetTicketBy() {
        var filmSession = new FilmSessionDto(1, "1 + 1", "Num1", LocalDateTime.now(), LocalDateTime.now(),
                200);
        var hallDto = new HallsDto(1, "Num1", 1, 2, "sdasd");
        when(service.findById(filmSession.getId())).thenReturn(Optional.of(filmSession));
        when(hallService.findByName(filmSession.getHallsName())).thenReturn(Optional.of(hallDto));

        var model = new ConcurrentModel();
        var view = controller.buyTickedPlace(model, filmSession.getId());
        var actualSession = model.getAttribute("filmSession");

        assertThat(view).isEqualTo("ticket/buy");
        assertThat(actualSession).isEqualTo(filmSession);
    }

    @Test
    void whenErrorThenGetTicketBy() {
        var filmSession = new FilmSessionDto(1, "1 + 1", "Num1", LocalDateTime.now(), LocalDateTime.now(),
                200);
        when(service.findById(filmSession.getId())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = controller.buyTickedPlace(model, filmSession.getId());
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("redirect:/error/404");
        assertThat(actualMessage).isEqualTo("Что-то пошло не так.");
    }

    @Test
    void whenErrorThenPostTicketSave() {
        String message = """
                Не удалось приобрести билет на заданное место.
                "Вероятно оно уже занято. Перейдите на страницу бронирования билетов
                и попробуйте снова.
                """;
        var ticket = new Ticket(1, 1, 1, 1, 1);
        when(ticketService.save(ticket)).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = controller.saveTicket(ticket, model);
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("redirect:/error/404");
        assertThat(actualMessage).isEqualTo(message);
    }

    @Test
    void whenFilmSessionThenPostSaveTicket() {
        var ticket = new Ticket(1, 1, 1, 1, 1);
        when(ticketService.save(ticket)).thenReturn(Optional.of(ticket));

        var model = new ConcurrentModel();
        var view = controller.saveTicket(ticket, model);
        var actualRow = model.getAttribute("row");
        var actualPlace = model.getAttribute("place");

        assertThat(view).isEqualTo("ticket/save");
        assertThat(actualRow).isEqualTo(ticket.getRowNumber());
        assertThat(actualPlace).isEqualTo(ticket.getPlaceNumber());
    }
}