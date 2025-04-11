package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("MPARatingFilmRowMapper")
public class MPARatingFilmRowMapper implements RowMapper<MPARatingFilm> {
    @Override
    public MPARatingFilm mapRow(ResultSet rs, int rowNum) throws SQLException {
        MPARatingFilm mpaRatingFilm = new MPARatingFilm();

        mpaRatingFilm.setId(rs.getLong("id"));
        mpaRatingFilm.setName(rs.getString("name"));

        return mpaRatingFilm;
    }
}
