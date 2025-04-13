package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.GenreDict;

import java.util.List;

public interface GenreDictStorage {
    GenreDict getById(Long id);

    List<GenreDict> getAll();
}
