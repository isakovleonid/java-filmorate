package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmLikesStorage;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmLikeService {
    private final FilmLikesStorage filmLikesStorage;
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public void add(Long filmId, Long userId) {
        userStorage.checkExists(userId);
        filmStorage.checkExists(filmId);

        filmLikesStorage.add(filmId, userId);
    }

    public void delete(Long filmId, Long userId) {
        userStorage.checkExists(userId);
        filmStorage.checkExists(filmId);

        filmLikesStorage.delete(filmId, userId);
    }

    public List<Film> getMostPopularFilms(Long filmCount) {
        return filmLikesStorage.getMostPopularFilms(filmCount).stream()
                .map(filmStorage::getFilm)
                .toList();
    }
}
