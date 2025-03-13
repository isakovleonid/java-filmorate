package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;

    public List<ru.yandex.practicum.filmorate.model.Film> getAll() {
        return filmStorage.getAll();
    }

    public ru.yandex.practicum.filmorate.model.Film add(@Valid @RequestBody ru.yandex.practicum.filmorate.model.Film newFilm) {
        return filmStorage.add(newFilm);
    }

    public ru.yandex.practicum.filmorate.model.Film update(@Valid @RequestBody ru.yandex.practicum.filmorate.model.Film newFilm) {
        return filmStorage.update(newFilm);
    }

    public void delete(@Valid @RequestBody ru.yandex.practicum.filmorate.model.Film film) {
        filmStorage.delete(film);
    }
}
