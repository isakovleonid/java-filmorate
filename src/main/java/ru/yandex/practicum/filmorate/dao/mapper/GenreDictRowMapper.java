package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.GenreDict;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("GenreDictRowMapper")
public class GenreDictRowMapper implements RowMapper<GenreDict> {
    @Override
    public GenreDict mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreDict genreDict = new GenreDict();

        genreDict.setId(rs.getLong("id"));
        genreDict.setName(rs.getString("name"));

        return genreDict;
    }
}
