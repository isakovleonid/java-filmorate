package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLException;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class FriendsRepository extends BaseRepository {
    private final String INSERT_QUERY = "insert into Friendship(userId, friendId, isaccepted) " +
            "values (?, ?, ?)";

    private final String ACCEPT_QUERY = "update Friendship " +
            " set isaccepted = ? " +
            " where userid = ? and friendid = ? or userid = ? and friendid = ?";

    private final String DELETE_QUERY = "delete Friendship " +
            " where userid = ? and friendid = ? or userid = ? and friendid = ?";

    private final String DELETE_ALL_BY_USER  = "delete Friendship " +
            " where userid = ? or friendid = ?";

    private final String FIND_ALL_FRIENDS_BY_USER = "select friendid from Friendship  where userid = ?" +
            " union " +
            " select userid from Friendship where friendid = ? and isaccepted = true";

    private final String FIND_ONE_BY_USER_FRIEND = "select * from Friendship  where userid = ? and friendid = ? and isaccepted = true" +
            " union select * from Friendship  where userid = ? and friendid = ? and isaccepted = true";

    private final String FIND_COMMON_FRIENDS = "(select friendid from Friendship  where userid = ? " +
            " union " +
            " select userid from Friendship where friendid = ? and isaccepted = true)" +
            " intersect " +
            " (select friendid from Friendship  where userid = ?" +
            " union " +
            " select userid from Friendship where friendid = ? and isaccepted = true)";

    @Autowired
    public FriendsRepository(JdbcTemplate jdbcTemplate, RowMapper<Friendship> mapper) {
        super(jdbcTemplate, mapper);
    }

    RowMapper<Long> longRowMapper = new RowMapper<Long>() {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong(1);
        }
    };

    public Friendship add(Long userId, Long friendId) {
        Boolean isAccepted = false;
        long id = insert(INSERT_QUERY, userId, friendId, isAccepted);

        Friendship friendship = new Friendship();

        friendship.setId(id);
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        friendship.setAccepted(isAccepted);

        return friendship;
    }

    public void delete(Long userId, Long friendId) {
        int rowDeleted = jdbcTemplate.update(DELETE_QUERY, userId, friendId, friendId, userId);

    }

    public void deleteByUserId(Long userId) {
        int rowDeleted = jdbcTemplate.update(DELETE_ALL_BY_USER, userId, userId);

    }

    public Friendship update(Friendship friendship, boolean isAccepted) {
        update(ACCEPT_QUERY,
                isAccepted,
                friendship.getUserId(),
                friendship.getFriendId(),
                friendship.getFriendId(),
                friendship.getUserId());

        friendship.setAccepted(isAccepted);

        return friendship;
    }

    public List<Long> findAllByUser(Long userId) {
        return jdbcTemplate.query(FIND_ALL_FRIENDS_BY_USER, longRowMapper, userId, userId);
    }

    public Optional<Friendship> findByUserFriend(Long userId, Long friendId) {
        return findOne(FIND_ONE_BY_USER_FRIEND, userId, friendId, friendId, userId);
    }

    public List<Long> findCommonFriends(Long user1Id, Long user2Id) {
        return jdbcTemplate.query(FIND_COMMON_FRIENDS, longRowMapper, user1Id, user1Id, user2Id, user2Id);
    }
}
