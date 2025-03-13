package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    private final FriendsStorage friendsStorage;

    public List<User> getAll() {
        return userStorage.getAll();
    }

    public User add(@Valid @RequestBody User newUser) {
        return userStorage.add(newUser);
    }

    public User update(@Valid @RequestBody User newUser) {
        return userStorage.update(newUser);
    }

    public void delete(@PathVariable("id") @NotNull Long userId) {
        // удаляем друзей пользователя
        friendsStorage.deleteUser(userId);
        userStorage.delete(userId);
    }
}
