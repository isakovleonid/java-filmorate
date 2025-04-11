package ru.yandex.practicum.filmorate.storage;

import java.util.List;

public interface FriendsStorage {
    void add(Long userId, Long friendId);

    void update(Long userId, Long friendId, Boolean isAccepted);

    void delete(Long userId, Long friendId);

    void deleteUser(Long userId);

    boolean isPresent(Long userId, Long friendId);

    List<Long> getAllUserFriends(Long userId);

    List<Long> getCommonFriends(Long userId, Long otherUserId);
}
