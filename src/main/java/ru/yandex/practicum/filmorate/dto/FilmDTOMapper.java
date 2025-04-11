package ru.yandex.practicum.filmorate.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.GenreDictRepository;
import ru.yandex.practicum.filmorate.dao.MPARatingFilmRepository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilmDTOMapper implements DTOMapper<FilmDTO, Film> {
    private final MPARatingFilmRepository mpaRatingFilmRepository;
    private final GenreDictRepository genreDictRepository;

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
            MPARatingFilm mpaRatingFilm = mpaRatingFilmRepository.getById(film.getMpa());
            filmDTO.setMpa(mpaRatingFilm);
        }

        Set<GenreDict> genreDictSet = film.getGenres().stream()
                        .filter(Objects::nonNull)
                        .map(g -> {
                            return genreDictRepository.getById(g); })
                        .collect(Collectors.toSet());
        filmDTO.setGenres(genreDictSet);

        return filmDTO;
    }
}
