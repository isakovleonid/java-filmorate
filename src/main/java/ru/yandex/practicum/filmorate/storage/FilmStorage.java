package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(Film film);

    Film update(Film film);

    void delete(Long id);

    List<Film> getAll();

    Film getFilm(Long id);

    boolean checkExists(Long id);
}
