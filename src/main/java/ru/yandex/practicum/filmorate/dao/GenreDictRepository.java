package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreDictRepository extends BaseRepository {
    private final String FIND_ALL = "select * from Genre";

    private final String FIND_ONE_BY_ID = "select * from Genre " +
            " where id = ?";

    @Autowired
    public GenreDictRepository(JdbcTemplate jdbcTemplate, RowMapper<GenreDict> mapper) {
        super(jdbcTemplate, mapper);
    }

    public GenreDict getById(Long id) {
        Optional<GenreDict> genre = findOne(FIND_ONE_BY_ID, id);

        if (genre.isEmpty())
            throw new FilmorateNotFoundException("Не найден жанр с id = " + id);

        return genre.get();
    }

    public List<GenreDict> getAll() {
        return findList(FIND_ALL);
    }
}
