package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(Film film);

    Film update(Film film);

    void delete(Film film);

    List<Film> getAll();

    Film getFilm(Long id);

    boolean checkExists(Long id);
}
