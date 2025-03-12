package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;

import java.util.*;

@Component
public class InMemoryFriendsStorage implements FriendsStorage{
    private final Map<Long, Set<Long>> friends = new HashMap<>();

    @Override
    public void add(Long userId, Long friendId) {
        if (!friends.containsKey(userId))
            friends.put(userId, new HashSet<>());

        friends.get(userId).add(friendId);
    }

    @Override
    public void delete(Long userId, Long friendId) {
        if (friends.containsKey(userId))
            friends.get(userId).remove(friendId);
    }

    // TODO: этот метод надо где-то вызвать при удалении пользователя.
    @Override
    public void deleteUser(Long userId) {
       if (friends.containsKey(userId)) {
           // удаляем пользователя из его друзей...
           for (Long i : friends.get(userId)) {
               delete(i, userId);
           }

           // ....а потом удаляем массив пользователя
           friends.remove(userId);
       }
    }

    @Override
    public List<Long> getAllUserFriends(Long userId) {
        List<Long> list = List.of();

        if (friends.containsKey(userId))
            list = friends.get(userId).stream()
                .toList();

       return list;
    }

    @Override
    public List<Long> getCommonFriends(Long userId, Long otherUserId) {
        List<Long> list = List.of();

        if (friends.containsKey(userId))
            list = friends.get(userId).stream()
                    .filter(friends.get(otherUserId)::contains)
                    .toList();

        return list;
    }
}
