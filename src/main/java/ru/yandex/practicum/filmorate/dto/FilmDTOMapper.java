package ru.yandex.practicum.filmorate.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.storage.GenreDictStorage;
import ru.yandex.practicum.filmorate.storage.MPARatingFilmStorage;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilmDTOMapper implements DTOMapper<FilmDTO, Film> {
    private final MPARatingFilmStorage mpaRatingFilmStorage;
    private final GenreDictStorage genreDictStorage;

    @Override
    public Film fromDTO(FilmDTO filmDTO) {
        Film film = new Film();
        film.setId(filmDTO.getId());
        film.setName(filmDTO.getName());
        film.setDescription(filmDTO.getDescription());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setDuration(filmDTO.getDuration());
        film.setMpa(filmDTO.getMpa().getId());

        film.setGenres(filmDTO.getGenres().stream()
                .map(GenreDict::getId)
                .collect(Collectors.toSet())
        );
        return film;
    }

    @Override
    public FilmDTO toDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setId(film.getId());
        filmDTO.setName(film.getName());
        filmDTO.setDescription(film.getDescription());
        filmDTO.setReleaseDate(film.getReleaseDate());
        filmDTO.setDuration(film.getDuration());

        if (film.getMpa() != null) {
            filmDTO.setMpa(mpaRatingFilmStorage.getById(film.getMpa()));
        }

        filmDTO.setGenres(genreDictStorage.getAllByFilm(filmDTO.getId()));

        return filmDTO;
    }
}
