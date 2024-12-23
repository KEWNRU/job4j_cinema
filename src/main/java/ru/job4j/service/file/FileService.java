package ru.job4j.service.file;

import ru.job4j.dto.FileDto;
import ru.job4j.model.File;

import java.util.Optional;

public interface FileService {

    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    void deleteById(int id);
}