package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    volatile Long maxId = 0L;
    Map<Long, Film> films = new HashMap<>();

    private long getNextId() {
        return ++maxId;
    }

    @GetMapping
    public List<Film> getAll() {
        return films.values().stream().toList();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film newFilm) {
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

        if (!films.containsKey(newFilm.getId())) {
            log.error("Не найден фильм");
            throw new FilmValidationException("Не найден фильм");
        }

        films.put(newFilm.getId(), newFilm);

        return newFilm;
    }
}
