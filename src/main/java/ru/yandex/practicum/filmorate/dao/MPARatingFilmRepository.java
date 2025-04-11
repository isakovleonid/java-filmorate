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
public class MPARatingFilmRepository extends BaseRepository {
    private final String FIND_ALL = "select * from MPARating";

    private final String FIND_ONE_BY_ID = "select * from MPARating " +
            " where id = ?";

    @Autowired
    public MPARatingFilmRepository(JdbcTemplate jdbcTemplate, RowMapper<MPARatingFilm> mapper) {
        super(jdbcTemplate, mapper);
    }

    public MPARatingFilm getById(Long id) {
        Optional<MPARatingFilm> mpa = findOne(FIND_ONE_BY_ID, id);

        if (mpa.isEmpty())
            throw new FilmorateNotFoundException("Не найден рейтинг с id = " + id);

        return mpa.get();
    }

    public List<MPARatingFilm> getAll() {
        return findList(FIND_ALL);
    }
}
