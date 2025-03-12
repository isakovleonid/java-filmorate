package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InMemoryFilmLikesStorage implements FilmLikesStorage {
    Map<Long, Set<Long>> filmlikes = new HashMap<>();

    @Override
    public void add(Long filmId, Long userId) {
        if (!filmlikes.containsKey(filmId))
            filmlikes.put(filmId, new HashSet<>());

        filmlikes.get(filmId).add(userId);
    }

    @Override
    public void delete(Long filmId, Long userId) {
        if (filmlikes.containsKey(filmId))
            filmlikes.get(filmId).remove(userId);
    }

    @Override
    public List<Long> getMostPopularFilms(Long filmCount) {
        return filmlikes.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
                .map(Map.Entry::getKey)
                .limit(filmCount)
                .toList();
    }
}
