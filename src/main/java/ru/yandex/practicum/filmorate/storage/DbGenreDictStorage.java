package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.GenreDictRepository;
import ru.yandex.practicum.filmorate.model.GenreDict;

import java.util.List;

@Component("DbGenreDictStorage")
@RequiredArgsConstructor
public class DbGenreDictStorage implements GenreDictStorage {
    private final GenreDictRepository genreDictRepository;

    @Override
    public List<GenreDict> getAll() {
        return genreDictRepository.getAll();
    }

    @Override
    public GenreDict getById(Long id) {
        return genreDictRepository.getById(id);
    }
}
