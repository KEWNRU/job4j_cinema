package ru.job4j.service.hall;

import org.springframework.stereotype.Repository;
import ru.job4j.dto.HallsDto;
import ru.job4j.model.Halls;
import ru.job4j.repository.hall.Sql2oHallRepository;

import java.util.Optional;

@Repository
public class SimpleHallService implements HallService {

    private final Sql2oHallRepository sql2oHallRepository;

    public SimpleHallService(Sql2oHallRepository sql2oHallRepository) {
        this.sql2oHallRepository = sql2oHallRepository;
    }

    @Override
    public Optional<Halls> findById(int id) {
        return sql2oHallRepository.findById(id);
    }

    @Override
    public Optional<HallsDto> findByName(String name) {
        Optional<Halls> halls = sql2oHallRepository.findByName(name);
        HallsDto hallsDto = null;
        if (halls.isPresent()) {
            hallsDto = new HallsDto(halls.get().getId(),
                    halls.get().getName(),
                    halls.get().getPlaceCount(),
                    halls.get().getRowCount(),
                    halls.get().getDescription());
        }
        return Optional.ofNullable(hallsDto);
    }

}
