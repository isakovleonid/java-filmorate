package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.model.GenreDict;

import java.util.Set;

public interface FilmGenreStorage {
    public FilmGenre add(FilmGenre filmGenre);

    Set<FilmGenre> getByFilm(Long id);

    public void delete_film(Long filmId);
}
