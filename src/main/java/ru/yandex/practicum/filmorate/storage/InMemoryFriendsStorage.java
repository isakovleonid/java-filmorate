package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;

import java.util.*;

@Component("InMemoryFriendsStorage")
public class InMemoryFriendsStorage implements FriendsStorage {
    private final Map<Long, Set<Long>> friends = new HashMap<>();

    @Override
    public void add(Long userId, Long friendId) {
        if (!friends.containsKey(userId)) {
            friends.put(userId, new HashSet<>());
        }

        friends.get(userId).add(friendId);
    }

    @Override
    public boolean isPresent(Long userId, Long friendId) {
        return friends.get(userId).contains(friendId);
    }

    @Override
    public void update(Long userId, Long friendId, Boolean isAccepted) {

    }

    @Override
    public void delete(Long userId, Long friendId) {
        if (friends.containsKey(userId)) {
            friends.get(userId).remove(friendId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        friends.remove(userId);
    }

    @Override
    public List<Long> getAllUserFriends(Long userId) {
        List<Long> list = List.of();

        if (friends.containsKey(userId)) {
            list = friends.get(userId).stream()
                    .toList();
        }

       return list;
    }

    @Override
    public List<Long> getCommonFriends(Long userId, Long otherUserId) {
        List<Long> list = List.of();

        if (friends.containsKey(userId)) {
            list = friends.get(userId).stream()
                    .filter(friends.get(otherUserId)::contains)
                    .toList();
        }

        return list;
    }
}
