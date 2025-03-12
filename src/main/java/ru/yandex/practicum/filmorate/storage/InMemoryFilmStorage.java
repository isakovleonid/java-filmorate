package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage{
    volatile Long maxId = 0L;
    Map<Long, Film> films = new HashMap<>();

    private long getNextId() {
        return ++maxId;
    }

    @Override
    public Film add(Film newFilm) {
        newFilm.setId(getNextId());

        films.put(newFilm.getId(), newFilm);

        return newFilm;
    }

    @Override
    public Film update(Film newFilm) {
        getFilm(newFilm.getId());

        films.put(newFilm.getId(), newFilm);

        return newFilm;
    }

    @Override
    public void delete(Film film) {
        checkExists(film.getId());

        films.remove(film.getId());
    }

    @Override
    public List<Film> getAll() {
        return films.values().stream().toList();
    }

    @Override
    public Film getFilm(Long id) {
        checkExists(id);

        return films.get(id);
    }

    @Override
    public boolean checkExists(Long id) {
        if (id == null) {
            log.error("Не указан id фильма");
            throw new FilmorateValidationException("Не указан id фильма");
        }

        if (!films.containsKey(id)) {
            log.error("Не найден фильм c id = " + id);
            throw new FilmorateNotFoundException("Не найден фильм с id = " + id);
        }

        return true;
    }
}
