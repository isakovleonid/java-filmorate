package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.FilmGenre;

import java.util.List;

@Repository
public class FilmGenreRepository extends BaseRepository {
    private static final String INSERT_QUERY = "insert into FilmGenre(filmId, genreId) " +
            "values (?, ?)";

    private static final String DELETE_FILM_QUERY = "delete FilmGenre " +
            " where filmId = ?";

    private static final String FIND_BY_FILM_QUERY = "select id, filmId, genreId from FilmGenre " +
            " where filmId = ?";

    public FilmGenreRepository(JdbcTemplate jdbcTemplate, RowMapper<FilmGenre> mapper) {
        super(jdbcTemplate, mapper);
    }

    public FilmGenre add(FilmGenre filmGenre) {
        long id = insert(INSERT_QUERY, filmGenre.getFilmId(), filmGenre.getGenreId());

        filmGenre.setId(id);

        return filmGenre;
    }

    public List<FilmGenre> findAllByFilmId(Long filmId) {
        return findList(FIND_BY_FILM_QUERY, filmId);
    }

    public void delete_film(Long filmId) {
        int rowDeleted = jdbcTemplate.update(DELETE_FILM_QUERY, filmId);
    }
}
