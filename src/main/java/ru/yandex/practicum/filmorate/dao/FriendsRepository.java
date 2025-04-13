package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class FriendsRepository extends BaseRepository {
    private static final String INSERT_QUERY = "insert into Friendship(userId, friendId) " +
            "values (?, ?)";

    private static final String DELETE_QUERY = "delete Friendship " +
            " where userid = ? and friendid = ?";

    private static final String DELETE_ALL_BY_USER  = "delete Friendship " +
            " where userid = ?";

    private static final String FIND_ALL_FRIENDS_BY_USER = "select friendid from Friendship  where userid = ?";

    private static final String FIND_ONE_BY_USER_FRIEND = "select * from Friendship  where userid = ? and friendid = ?";

    private static final String FIND_COMMON_FRIENDS = "select friendid from Friendship  where userid = ? " +
            " intersect " +
            " select friendid from Friendship  where userid = ?";

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
        long id = insert(INSERT_QUERY, userId, friendId);

        Friendship friendship = new Friendship();

        friendship.setId(id);
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);

        return friendship;
    }

    public void delete(Long userId, Long friendId) {
        int rowDeleted = jdbcTemplate.update(DELETE_QUERY, userId, friendId);

    }

    public void deleteByUserId(Long userId) {
        int rowDeleted = jdbcTemplate.update(DELETE_ALL_BY_USER, userId);
    }

    public List<Long> findAllByUser(Long userId) {
        return jdbcTemplate.query(FIND_ALL_FRIENDS_BY_USER, longRowMapper, userId);
    }

    public Optional<Friendship> findByUserFriend(Long userId, Long friendId) {
        return findOne(FIND_ONE_BY_USER_FRIEND, userId, friendId);
    }

    public List<Long> findCommonFriends(Long user1Id, Long user2Id) {
        return jdbcTemplate.query(FIND_COMMON_FRIENDS, longRowMapper, user1Id, user2Id);
    }
}
