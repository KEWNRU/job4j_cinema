package ru.job4j.service.hall;

import ru.job4j.dto.HallsDto;
import ru.job4j.model.Halls;

import java.util.Optional;

public interface HallService {
    Optional<Halls> findById(int id);
    Optional<HallsDto> findByName(String name);
}
