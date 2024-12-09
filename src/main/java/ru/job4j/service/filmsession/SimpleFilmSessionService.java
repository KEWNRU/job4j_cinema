package ru.job4j.service.filmsession;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FilmSessionDto;
import ru.job4j.repository.film.Sql2oFilmRepository;
import ru.job4j.repository.filmsession.Sql2oFilmSessionRepository;
import ru.job4j.repository.hall.Sql2oHallRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final Sql2oFilmSessionRepository filmSessionRepository;
    private final Sql2oHallRepository sql2oHallRepository;
    private final Sql2oFilmRepository sql2oFilmRepository;

    public SimpleFilmSessionService(Sql2oFilmSessionRepository filmSessionRepository,
                                    Sql2oHallRepository sql2oHallRepository,
                                    Sql2oFilmRepository sql2oFilmRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.sql2oHallRepository = sql2oHallRepository;
        this.sql2oFilmRepository = sql2oFilmRepository;
    }

    @Override
    public Optional<FilmSessionDto> findById(int id) {
        var filmSession = filmSessionRepository.findById(id);
        FilmSessionDto filmSessionDto = null;
        if (filmSession.isPresent()) {
            filmSessionDto = new FilmSessionDto(
                    filmSession.get().getId(),
                    sql2oFilmRepository.findById(filmSession.get().getId()).get().getName(),
                    sql2oHallRepository.findById(filmSession.get().getId()).get().getName(),
                    filmSession.get().getStartTime(),
                    filmSession.get().getEndTime(),
                    filmSession.get().getPrice());
        }
        return Optional.ofNullable(filmSessionDto);
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        return filmSessionRepository.findAll().stream().map(filmSessions -> new FilmSessionDto(
                filmSessions.getId(),
                sql2oFilmRepository.findById(filmSessions.getFilmId()).get().getName(),
                sql2oHallRepository.findById(filmSessions.getHallsId()).get().getName(),
                filmSessions.getStartTime(),
                filmSessions.getEndTime(),
                filmSessions.getPrice())).collect(Collectors.toList());
    }
}
