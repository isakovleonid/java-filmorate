package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.LocalDateFormatter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
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
        return /*film.getName() != null
                && !film.getName().isBlank()
                && (film.getDescription() == null || film.getDescription().length() <= MAX_LEN_DESCRIPTION)
                &&*/ !film.getReleaseDate().isBefore(MIN_RELEASE_DATE)
               /* && film.getDuration() != null
                && film.getDuration() > 0*/;
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
        if (newFilm.getId() == null)
            throw new FilmValidationException("Не указан id фильма");

        if (films.containsKey(newFilm.getId())) {
            if (!isValidFilm(newFilm))
                throw new FilmValidationException("Не пройдена проверка атрибутов фильма");

            films.put(newFilm.getId(), newFilm);

            return newFilm;
        }

        throw new FilmValidationException("Не найден фильм");
    }

    @Bean
    @Primary
    public Formatter<LocalDate> localDateFormatter() {
        return new LocalDateFormatter();
    }
}
