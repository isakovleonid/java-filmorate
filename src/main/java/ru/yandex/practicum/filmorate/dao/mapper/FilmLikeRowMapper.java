package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.FilmLike;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("FilmLikeRowMapper")
public class FilmLikeRowMapper implements RowMapper<FilmLike> {
    @Override
    public FilmLike mapRow(ResultSet rs, int rowNum) throws SQLException {
        FilmLike filmLike = new FilmLike();

        filmLike.setId(rs.getLong("id"));
        filmLike.setFilmId(rs.getLong("filmId"));
        filmLike.setUserId(rs.getLong("userId"));

        return filmLike;
    }
}
