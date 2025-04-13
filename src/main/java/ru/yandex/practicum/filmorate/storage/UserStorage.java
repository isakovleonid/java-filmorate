package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User add(User user);

    User update(User user);

    void delete(Long id);

    List<User> getAll();

    User getUser(Long id);

    boolean checkExists(Long id);

    default void fillAttrByDefault(final User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
    }
}
