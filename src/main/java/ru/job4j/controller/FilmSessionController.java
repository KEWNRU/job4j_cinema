package ru.job4j.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.service.filmsession.FilmSessionService;

@ThreadSafe
@Controller()
@RequestMapping("/session")
public class FilmSessionController {
    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping
    private String getAll(Model model) {
        model.addAttribute("filmSession", filmSessionService.findAll());
        return "session/index";
    }
}
