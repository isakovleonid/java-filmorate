package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmLikeRepository extends BaseRepository {
    private final String INSERT_QUERY = "insert into FilmLike(filmId, userId) " +
            "values (?, ?)";

    private final String DELETE_QUERY = "delete FilmLike " +
            " where filmId = ? and userid = ?";

    private final String DELETE_FILM_QUERY = "delete FilmLike " +
            " where filmId = ?";

    private final String FIND_MOST_POPULAR = "select fr.filmId\n" +
            "             from \n" +
            "                    FilmLike fr\n" +
            "             group by \n" +
            "                    fr.filmId\n" +
            "             order by\n" +
            "                    count(fr.filmId) desc\n" +
            "             limit ?";


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
        return jdbcTemplate.query(FIND_MOST_POPULAR, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        }, filmCount);
    }
}
