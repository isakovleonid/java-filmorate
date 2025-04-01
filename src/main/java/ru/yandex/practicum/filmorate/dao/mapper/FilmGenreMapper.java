package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.FilmGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("FilmGenreMapper")
public class FilmGenreMapper implements RowMapper<FilmGenre> {
    @Override
    public FilmGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
        FilmGenre filmGenre = new FilmGenre();

        filmGenre.setId(rs.getLong("id"));
        filmGenre.setFilmId(rs.getLong("filmId"));
        filmGenre.setGenreId(rs.getString("genreId"));

        return filmGenre;
    }
}
