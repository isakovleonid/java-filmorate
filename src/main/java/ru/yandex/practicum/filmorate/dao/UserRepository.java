package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository{
    private final String INSERT_QUERY = "insert into Users(name, login, birthday, email) " +
            "values (?, ?, ?, ?)";

    private final String UPDATE_QUERY = "update Users " +
            " set name = ?, login = ?, birthday = ?, email = ? " +
            " where id = ?";

    private final String DELETE_QUERY = "delete Users " +
            " where id = ?";

    private final String FIND_ALL = "select * from Users";

    private final String FIND_ONE_BY_ID = "select * from Users " +
            " where id = ?";

    private final String FIND_ONE_BY_LOGIN = "select * from Users " +
            " where login = ?";

    private final String FIND_ONE_BY_EMAIL = "select * from Users " +
            " where email = ?";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, @Qualifier("RowMapperUser") RowMapper<User> mapper) {
        super(jdbcTemplate, mapper);
    }

    public User add(User user) {
        long id = insert(INSERT_QUERY, user.getName(), user.getLogin(), user.getBirthday(), user.getEmail());

        user.setId(id);

        return user;
    }

    public User update(User user) {
        update(UPDATE_QUERY,
                user.getName(),
                user.getLogin(),
                user.getBirthday().format(DateTimeFormatter.ISO_DATE),
                user.getEmail(),
                user.getId()
            );

        return user;
    }

    public void delete(Long id) {
        delete(DELETE_QUERY, id);
    }

    public List<User> findAll() {
        return findList(FIND_ALL);
    }

    public Optional<User> findById(Long id) {
        return findOne(FIND_ONE_BY_ID, id);
    }

    public Optional<User> findByLogin(String login) {
        return findOne(FIND_ONE_BY_LOGIN, login);
    }

    public Optional<User> findByEmail(String email) {
        return findOne(FIND_ONE_BY_EMAIL, email);
    }
}
