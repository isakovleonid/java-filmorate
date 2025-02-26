package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    Map<Long, Film> films = new HashMap<>();

    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    private long getNextId() {
        long currentMaxId = films.values().stream()
                .mapToLong(Film::getId)
                .max()
                .orElse(0);

        return ++currentMaxId;
    }

    private boolean isValidFilm(Film film) {
        return !film.getReleaseDate().isBefore(MIN_RELEASE_DATE);
    }

    @GetMapping
    public Collection<Film> getAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film newFilm) {
        if (!isValidFilm(newFilm))
            throw new FilmValidationException("Не пройдена проверка атрибутов фильма");

        newFilm.setId(getNextId());

        films.put(newFilm.getId(), newFilm);

        return newFilm;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) {
        if (newFilm.getId() == null) {
            log.error("Не указан id фильма");
            throw new FilmValidationException("Не указан id фильма");
        }

        if (films.containsKey(newFilm.getId())) {
            if (!isValidFilm(newFilm)) {
                log.error("Не пройдена проверка атрибутов фильма");
                throw new FilmValidationException("Не пройдена проверка атрибутов фильма");
            }

            films.put(newFilm.getId(), newFilm);

            return newFilm;
        }

        log.error("Не найден фильм");
        throw new FilmValidationException("Не найден фильм");
    }
}
