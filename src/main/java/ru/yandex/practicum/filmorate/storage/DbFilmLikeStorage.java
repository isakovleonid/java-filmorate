package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FilmLikeRepository;

import java.util.List;

@Component("DbFilmLikesStorage")
@RequiredArgsConstructor
public class DbFilmLikeStorage implements FilmLikesStorage {
    private final FilmLikeRepository filmLikeRepository;

    @Override
    public void add(Long filmId, Long userId) {
        filmLikeRepository.add(filmId, userId);
    }

    @Override
    public void deleteAllFilmLikes(Long filmId) {
        filmLikeRepository.delete_film(filmId);
    }

    @Override
    public void delete(Long filmId, Long userId) {
        filmLikeRepository.delete(filmId, userId);
    }

    @Override
    public List<Long> getMostPopularFilms(Long filmCount) {
        return filmLikeRepository.findMostPopularFilms(filmCount);
    }
}
