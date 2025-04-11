package ru.yandex.practicum.filmorate.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("FriendsRowMapper")
public class FriendsRowMapper implements RowMapper<Friendship> {
    @Override
    public Friendship mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friendship friendship = new Friendship();

        friendship.setId(rs.getLong("id"));
        friendship.setUserId(rs.getLong("userid"));
        friendship.setFriendId(rs.getLong("friendid"));
        friendship.setAccepted(rs.getBoolean("isaccepted"));

        return friendship;
    }
}
