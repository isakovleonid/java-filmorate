package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    volatile Long maxId = 0L;
    Map<Long, User> users = new HashMap<>();

    private long getNextId() {
        return ++maxId;
    }

    private void fillAttrByDefault(final User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
    }

    @Override
    public User add(User newUser) {
        newUser.setId(getNextId());

        fillAttrByDefault(newUser);

        users.put(newUser.getId(), newUser);

        return newUser;
    }

    @Override
    public void delete(Long userId) {
        checkExists(userId);

        users.remove(userId);
    }

    @Override
    public User update(User newUser) {
        checkExists(newUser.getId());

        fillAttrByDefault(newUser);
        users.put(newUser.getId(), newUser);

        return newUser;
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().toList();
    }

    @Override
    public User getUser(Long id) {
        checkExists(id);

        return users.get(id);
    }

    public boolean checkExists(Long id) {
        if (id == null) {
            log.error("Не указан id пользователя");
            throw new FilmorateValidationException("Не указан id пользователя");
        }

        if (!users.containsKey(id)) {
            log.error("Не найден пользователь c id = " + id);
            throw new FilmorateNotFoundException("Не найден пользователь c id = " + id);
        }

        return true;
    }
}
