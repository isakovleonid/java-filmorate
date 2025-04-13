package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.storage.FilmGenreStorage;

@Service
public class FilmGenreService {
    private final FilmGenreStorage filmGenreStorage;

    @Autowired
    public FilmGenreService(@Qualifier("DbFilmGenreStorage") FilmGenreStorage filmGenreStorage) {
        this.filmGenreStorage = filmGenreStorage;
    }

    public FilmGenre add(FilmGenre filmGenre) {
        return filmGenreStorage.add(filmGenre);
    }

    void delete_film(Long filmId) {
        filmGenreStorage.delete_film(filmId);
    }
}
