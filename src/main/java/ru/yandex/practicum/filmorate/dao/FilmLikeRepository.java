package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmLikeRepository extends BaseRepository {
    private final String INSERT_QUERY = "insert into FilmLike(filmId, userId) " +
            "values (?, ?)";

    private final String DELETE_QUERY = "delete FilmLike " +
            " where filmId = ? and userid = ?";

    private final String DELETE_FILM_QUERY = "delete FilmLike " +
            " where filmId = ?";

    private final String FIND_ALL = "select * from FilmLike";

    private final String FIND_ONE_BY_ID = "select * from FilmLike " +
            "where id = ?";

    private final String FIND_ONE_BY_FILM_USER = "select * from FilmLike " +
            "where filmId = ? and userId = ?";

    public FilmLikeRepository(JdbcTemplate jdbcTemplate, @Qualifier("FilmLikeRowMapper") RowMapper mapper) {
        super(jdbcTemplate, mapper);
    }

    public void add(Long filmId, Long userId) {
        long id = insert(INSERT_QUERY, filmId, userId);
    }

    public void delete(Long filmId, Long userId) {
        int rowDeleted = jdbcTemplate.update(DELETE_QUERY, filmId, userId);

        if (rowDeleted == 0) {
            throw new FilmorateSQLException("Не удалось удалить данные");
        }
    }

    public void delete_film(Long filmId) {
        int rowDeleted = jdbcTemplate.update(DELETE_FILM_QUERY, filmId);

        if (rowDeleted == 0) {
            throw new FilmorateSQLException("Не удалось удалить данные");
        }
    }

    public List<Long> findMostPopularFilms(Long filmCount) {
        return findList(FIND_ALL);
    }
}
