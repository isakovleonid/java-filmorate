package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmLikesStorage;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final FilmLikesStorage filmLikesStorage;

    @Autowired
    public FilmService(@Qualifier("DbFilmStorage") FilmStorage filmStorage,
                       @Qualifier("DbFilmLikesStorage") FilmLikesStorage filmLikesStorage) {
        this.filmStorage = filmStorage;
        this.filmLikesStorage = filmLikesStorage;
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }

    public Film add(Film newFilm) {
        return filmStorage.add(newFilm);
    }

    public Film update(Film newFilm) {
        return filmStorage.update(newFilm);
    }

    public void delete(Long id) {
        filmLikesStorage.deleteAllFilmLikes(id);

        filmStorage.delete(id);
    }
}
