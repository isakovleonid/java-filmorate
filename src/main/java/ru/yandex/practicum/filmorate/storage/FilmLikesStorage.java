package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.FilmLike;

import java.util.List;

public interface FilmLikesStorage {
    void add(Long filmId, Long userId);

    void delete(Long filmId, Long userId);

    void deleteAllFilmLikes(Long filmId);

    List<Long> getMostPopularFilms(Long filmCount);
}
