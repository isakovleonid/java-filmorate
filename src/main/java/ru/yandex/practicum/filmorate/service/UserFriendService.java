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
public class UserFriendService {
    private final FriendsStorage  friends;
    private final UserStorage userStorage;

    @Autowired
    public UserFriendService(@Qualifier("DbUserStorage") UserStorage userStorage, FriendsStorage friends) {
        this.userStorage = userStorage;
        this.friends = friends;
    }

    public void addFriend(Long userId, Long friendId) {
        userStorage.checkExists(userId);
        userStorage.checkExists(friendId);

        // друзья должны быть взаимно
        friends.add(userId, friendId);
        friends.add(friendId, userId);
    }

    public void deleteFriend(Long userId, Long friendId) {
        userStorage.checkExists(userId);
        userStorage.checkExists(friendId);

        // При удалении друзей связь удаляется взаимно
        friends.delete(userId, friendId);
        friends.delete(friendId, userId);
    }

    public void deleteUser(Long userId) {
        userStorage.checkExists(userId);

        friends.deleteUser(userId);
    }

    public List<User> getAllUserFriends(Long userId) {
        userStorage.checkExists(userId);

        return friends.getAllUserFriends(userId).stream()
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
