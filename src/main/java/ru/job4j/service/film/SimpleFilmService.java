package ru.job4j.service.film;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FilmDto;
import ru.job4j.repository.file.FileRepository;
import ru.job4j.repository.film.FilmRepository;
import ru.job4j.repository.film.Sql2oFilmRepository;
import ru.job4j.repository.genres.GenresRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository sql2oFilmRepository;
    private final GenresRepository genresRepository;
    private final FileRepository fileRepository;


    public SimpleFilmService(Sql2oFilmRepository sql2oFilmRepository, GenresRepository genresRepository, FileRepository fileRepository) {
        this.sql2oFilmRepository = sql2oFilmRepository;
        this.genresRepository = genresRepository;
        this.fileRepository = fileRepository;
    }


    @Override
    public Optional<FilmDto> findById(int id) {
        var optionalFilm = sql2oFilmRepository.findById(id);
        if (optionalFilm.isPresent()) {
            var film = optionalFilm.get();
            FilmDto filmDto = new FilmDto(film.getId(),
                    genresRepository.findById(film.getId()).get().getName(),
                    film.getDurationInMinutes(),
                    film.getMinimalAge(),
                    film.getYear(),
                    film.getDescription(),
                    film.getName(),
                    film.getFileId());
            return Optional.ofNullable(filmDto);
        }
        return Optional.empty();
    }

    @Override
    public Collection<FilmDto> findAll() {
        return sql2oFilmRepository.findAll().stream().map(film ->
                new FilmDto(film.getId(),
                        genresRepository.findById(film.getId()).get().getName(),
                        film.getDurationInMinutes(),
                        film.getMinimalAge(),
                        film.getYear(),
                        film.getDescription(),
                        film.getName(),
                        film.getFileId())).collect(Collectors.toList());
    }
}
