package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("UserRowMapper")
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setName((rs.getString("name")));
        user.setLogin(rs.getString("login"));
        user.setEmail((rs.getString("email")));
        Date dt = rs.getDate("birthday");
        user.setBirthday(dt.toLocalDate());

        return user;
    }
}
