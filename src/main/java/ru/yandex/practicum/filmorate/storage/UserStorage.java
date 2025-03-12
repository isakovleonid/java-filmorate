package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User add(User user);
    User update(User user);
    void delete(Long userId);
    List<User> getAll();
    User getUser(Long id);
    boolean checkExists(Long id);
}
