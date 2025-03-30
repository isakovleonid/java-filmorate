package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component("InMemoryFilmLikesStorage")
public class InMemoryFilmLikesStorage implements FilmLikesStorage {
    Map<Long, Set<Long>> filmLikes = new HashMap<>();

    @Override
    public void add(Long filmId, Long userId) {
        if (!filmLikes.containsKey(filmId)) {
            filmLikes.put(filmId, new HashSet<>());
        }

        filmLikes.get(filmId).add(userId);
    }

    @Override
    public void delete(Long filmId, Long userId) {
        if (filmLikes.containsKey(filmId)) {
            filmLikes.get(filmId).remove(userId);
        }
    }

    @Override
    public void delete(Long filmId) {
        filmLikes.remove(filmId);
    }

    @Override
    public List<Long> getMostPopularFilms(Long filmCount) {
        return filmLikes.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
                .map(Map.Entry::getKey)
                .limit(filmCount)
                .toList();
    }

}
