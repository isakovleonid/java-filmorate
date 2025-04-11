package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.GenreDict;

import java.util.List;

public interface GenreDictStorage {
    public GenreDict getById(Long id);

    public List<GenreDict> getAll();
}
