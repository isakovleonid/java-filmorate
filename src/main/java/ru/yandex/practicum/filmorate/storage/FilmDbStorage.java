package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FilmRepository;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@Component("DbFilmStorage")
@RequiredArgsConstructor
@Slf4j
public class FilmDbStorage implements FilmStorage {
    private final FilmRepository filmRepository;

    @Override
    public Film add(Film film) {
        return filmRepository.add(film);
    }

    @Override
    public Film update(Film film) {
        return filmRepository.update(film);
    }

    @Override
    public void delete(Long id) {
        filmRepository.delete(id);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilm(Long id) {
        return null;
    }

    @Override
    public boolean checkExists(Long id) {
        if (id == null) {
            log.error("Не указан id пользователя");
            throw new FilmorateValidationException("Не указан id фильма");
        }

        return true;
    }
}
