package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.util.List;

public interface MPARatingFilmStorage {
    public MPARatingFilm getById(Long id);

    public List<MPARatingFilm> getAll();
}
