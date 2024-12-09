package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.service.film.FilmService;


@ThreadSafe
@Controller()
@RequestMapping("/film")
public class FilmController {
    public final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public String getFilmAll(Model model) {
        model.addAttribute("film", filmService.findAll());
       return "film/index";
    }

    @GetMapping("/{id}")
    public String getFilm(Model model, @PathVariable int id) {
        model.addAttribute("film", filmService.findById(id).get());
        return "film/one";
    }
}
