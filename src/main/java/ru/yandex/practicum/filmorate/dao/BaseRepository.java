package ru.yandex.practicum.filmorate.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<T> {
    protected final JdbcTemplate jdbcTemplate;
    protected  final RowMapper<T> mapper;

    protected long insert(String query, Object... params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement st = connection.prepareStatement(
              query, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }

            return st;
        }, keyHolder);

        Long id = keyHolder.getKeyAs(Long.class);

        if (id != null) {
            return id;
        } else {
            throw new FilmorateSQLException("Не удалось сохранить данные");
        }
    }

    protected void update(String query, Object... params) {
        int rowUpdated = jdbcTemplate.update(query, params);

        if (rowUpdated == 0) {
            throw new FilmorateSQLException("Не удалось обновить данные");
        }
    }

    protected void delete(String query, Long id) {
        int rowDeleted = jdbcTemplate.update(query, id);

        if (rowDeleted == 0) {
            throw new FilmorateSQLException("Не удалось удалить данные");
        }
    }

    protected Optional<T> findOne(String query, Object... params) {
        try {
            T result = jdbcTemplate.queryForObject(query, mapper, params);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    protected List<T> findList(String query, Object... params) {
        return jdbcTemplate.query(query, mapper, params);
    }
}
