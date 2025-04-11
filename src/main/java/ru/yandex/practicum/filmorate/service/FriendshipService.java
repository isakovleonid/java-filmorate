package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendsStorage  friends;
    private final UserStorage userStorage;

    @Autowired
    public FriendshipService(@Qualifier("DbUserStorage") UserStorage userStorage, @Qualifier("DbFriendsStorage") FriendsStorage friends) {
        this.userStorage = userStorage;
        this.friends = friends;
    }

    public void add(Long userId, Long friendId) {
        userStorage.checkExists(userId);
        userStorage.checkExists(friendId);

        if (friends.isPresent(userId, friendId)) {
            friends.update(userId, friendId, true);
        } else {
            friends.add(userId, friendId);
        }
    }

    public void delete(Long userId, Long friendId) {
        userStorage.checkExists(userId);
        userStorage.checkExists(friendId);

        friends.delete(userId, friendId);
    }

    public void deleteUser(Long userId) {
        userStorage.checkExists(userId);

        friends.deleteUser(userId);
    }

    public List<User> getAllUserFriends(Long userId) {
        userStorage.checkExists(userId);

        List<Long> allFriendsid = friends.getAllUserFriends(userId);
        return allFriendsid.stream()
                .map(userStorage::getUser)
                .toList();
    }

    public List<User> getCommonFriends(Long userId, Long otherUserId) {
        userStorage.checkExists(userId);
        userStorage.checkExists(otherUserId);

        return friends.getCommonFriends(userId, otherUserId).stream()
                .map(userStorage::getUser)
                .toList();

    }
}
