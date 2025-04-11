package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.storage.GenreDictStorage;

import java.util.List;

@Service
public class GenreDictService {
    private GenreDictStorage genreStorage;

    @Autowired
    public GenreDictService(@Qualifier("DbGenreDictStorage") GenreDictStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    public GenreDict getById(Long id) {
        return genreStorage.getById(id);
    }

    public List<GenreDict> getAll() {
        return genreStorage.getAll();
    }
}
