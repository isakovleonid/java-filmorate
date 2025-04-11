package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.storage.FilmGenreStorage;
import ru.yandex.practicum.filmorate.storage.FilmLikesStorage;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final FilmLikesStorage filmLikesStorage;
    private final FilmGenreStorage filmGenreStorage;

    @Autowired
    public FilmService(@Qualifier("DbFilmStorage") FilmStorage filmStorage,
                       @Qualifier("DbFilmLikesStorage") FilmLikesStorage filmLikesStorage,
                       @Qualifier("DbFilmGenreStorage") FilmGenreStorage filmGenreStorage) {
        this.filmStorage = filmStorage;
        this.filmLikesStorage = filmLikesStorage;
        this.filmGenreStorage = filmGenreStorage;
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }


    public Film getFilm(Long id) {
        Film resFilm = filmStorage.getFilm(id);

        Set<Long> genresSet = filmGenreStorage.getByFilm(id).stream()
                .map(g -> {return g.getGenreId();})
                .collect(Collectors.toSet());

        resFilm.setGenres(genresSet);

        return resFilm;
    }

    public Film add(Film newFilm) {
        Film resFilm = filmStorage.add(newFilm);
/*
        newFilm.getGenres().stream()
                        .map(g -> {
                            FilmGenre filmGenre = new FilmGenre();
                            filmGenre.setGenreId(g);
                            filmGenre.setFilmId(resFilm.getId());
                            return  filmGenre;})
                        .peek(filmGenreStorage::add)
                        .close();*/

        for (Long genreId : newFilm.getGenres()) {
            FilmGenre filmGenre = new FilmGenre();
            filmGenre.setGenreId(genreId);
            filmGenre.setFilmId(resFilm.getId());
            filmGenreStorage.add(filmGenre);
        }

        return resFilm;
    }

    public Film update(Film newFilm) {
       /* Set<GenreDict> oldFilmGenreSet = newFilm.getGenres();*/

        filmGenreStorage.delete_film(newFilm.getId());

        Film updFilm = filmStorage.update(newFilm);

        Set<Long> updFilmGenreSet = newFilm.getGenres();
        updFilm.setGenres(updFilmGenreSet);
/*
        updFilm.getGenres().stream()
                .map(g -> {FilmGenre filmGenre = new FilmGenre();
                    filmGenre.setGenreId(g);
                    filmGenre.setFilmId(updFilm.getId());
                    return  filmGenre;})
                .peek(filmGenreStorage::add)
                .close();*/

        return updFilm;
    }

    public void delete(Long id) {
        filmLikesStorage.deleteAllFilmLikes(id);
        filmGenreStorage.delete_film(id);
        filmStorage.delete(id);
    }
}
