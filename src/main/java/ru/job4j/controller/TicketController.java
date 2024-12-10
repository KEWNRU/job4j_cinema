package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sql2o.Sql2oException;
import ru.job4j.dto.FilmSessionDto;
import ru.job4j.dto.HallsDto;
import ru.job4j.model.Ticket;
import ru.job4j.repository.ticket.Sql2oTicketRepository;
import ru.job4j.service.filmsession.FilmSessionService;
import ru.job4j.service.hall.HallService;
import ru.job4j.service.ticket.TicketService;

import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final FilmSessionService filmSessionService;
    private final HallService hallService;

    public TicketController(TicketService ticketService, FilmSessionService filmSessionService,
                            HallService hallService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
    }

    @GetMapping("/buy/{id}")
    public String buyTickedPlace(Model model, @PathVariable int id) {
        Optional<FilmSessionDto> filmSessionDto = filmSessionService.findById(id);
        if (filmSessionDto.isPresent()) {
            var filmSession = filmSessionDto.get();
            Optional<HallsDto> hallDto = hallService.findByName(filmSession.getHallsName());
            model.addAttribute("rowCounts", hallDto.get().getRowCount());
            model.addAttribute("placeCounts", hallDto.get().getPlaceCount());
            model.addAttribute("filmSession", filmSession);
            return "ticket/buy";

        }
        model.addAttribute("message", "Что-то пошло не так.");
        return "redirect:/error/404";
    }

    @PostMapping("/buy")
    public String saveTicket(@ModelAttribute Ticket ticket, Model model) {
        var optionalTicket = ticketService.save(ticket);
        if (optionalTicket.isEmpty()) {
            model.addAttribute("message", """
                    Не удалось приобрести билет на заданное место.
                    "Вероятно оно уже занято. Перейдите на страницу бронирования билетов
                    и попробуйте снова.
                    """);
            return "ticket/buy";
        }
        model.addAttribute("row", optionalTicket.get().getRowNumber());
        model.addAttribute("place", optionalTicket.get().getPlaceNumber());
        return "ticket/save";
    }
}