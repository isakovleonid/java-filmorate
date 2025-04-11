package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FilmGenreRepository;
import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.model.GenreDict;

import java.util.Set;
import java.util.stream.Collectors;

@Component("DbFilmGenreStorage")
@RequiredArgsConstructor
public class DbFilmGenreStorage implements FilmGenreStorage {
    private final FilmGenreRepository filmGenreRepository;

    @Override
    public FilmGenre add(FilmGenre filmGenre) {
        return filmGenreRepository.add(filmGenre);
    }

    @Override
    public Set<FilmGenre> getByFilm(Long id) {
        return filmGenreRepository.findAllByFilmId(id).stream().collect(Collectors.toSet());
    }

    @Override
    public void delete_film(Long filmId) {
        filmGenreRepository.delete_film(filmId);
    }
}
