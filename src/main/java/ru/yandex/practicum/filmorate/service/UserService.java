package ru.yandex.practicum.filmorate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final FriendsStorage friendsStorage;

    @Autowired
    public UserService(@Qualifier("DbFriendsStorage") FriendsStorage friendsStorage, @Qualifier("DbUserStorage") UserStorage userStorage) {
        this.friendsStorage = friendsStorage;
        this.userStorage = userStorage;
    }

    public List<User> getAll() {
        return userStorage.getAll();
    }

    public User add(User newUser) {
        return userStorage.add(newUser);
    }

    public User update(User newUser) {
        return userStorage.update(newUser);
    }

    public void delete(Long userId) {
        // удаляем друзей пользователя
        friendsStorage.deleteUser(userId);
        userStorage.delete(userId);
    }
}
