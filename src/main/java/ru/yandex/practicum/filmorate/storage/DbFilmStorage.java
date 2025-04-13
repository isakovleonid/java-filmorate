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
public class DbFilmStorage implements FilmStorage {
    private final FilmRepository filmRepository;

    @Override
    public Film add(Film film) {
        return filmRepository.add(film);
    }

    @Override
    public Film update(Film film) {
        checkExists(film.getId());

        return filmRepository.update(film);
    }

    @Override
    public void delete(Long id) {
        checkExists(id);

        filmRepository.delete(id);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilm(Long id) {
        if (filmRepository.findById(id).isEmpty()) {
            throw new FilmorateNotFoundException("Пользователь не найден");
        }
        else {
            return filmRepository.findById(id).get();
        }
    }

    @Override
    public boolean checkExists(Long id) {
        if (id == null) {
            log.error("Не указан id фильма");
            throw new FilmorateValidationException("Не указан id фильма");
        }

        if (filmRepository.findById(id).isEmpty()) {
            log.error("Не найден фильм c id = {}", id);
            throw new FilmorateNotFoundException("Не найден фильм c id = " + id);
        }

        return true;
    }
}
