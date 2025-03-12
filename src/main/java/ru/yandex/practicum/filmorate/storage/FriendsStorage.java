package ru.yandex.practicum.filmorate.storage;

import java.util.List;

public interface FriendsStorage {
    void add(Long userId, Long friendId);
    void delete(Long userId, Long friendId);
    void deleteUser(Long userId);
    List<Long> getAllUserFriends(Long userId);
    List<Long> getCommonFriends(Long userId, Long otherUserId);
}
