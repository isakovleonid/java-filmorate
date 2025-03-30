package ru.yandex.practicum.filmorate.storage;

import java.util.List;

public interface FilmLikesStorage {
    void add(Long filmId, Long userId);

    void delete(Long filmId, Long userId);

    void delete(Long filmId);

    List<Long> getMostPopularFilms(Long filmCount);
}
