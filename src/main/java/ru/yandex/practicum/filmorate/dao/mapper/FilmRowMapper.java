package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("FilmRowMapper")
public class FilmRowMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        Film film = new Film();

        film.setId(rs.getLong("id"));
        film.setName(rs.getString("name"));
        film.setDescription(rs.getString("description"));
        film.setDuration(rs.getLong("duration"));
        film.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
        film.setMpa(rs.getLong("MPArating"));

        return film;
    }
}
