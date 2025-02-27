package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    volatile Long maxId = 0L;
    Map<Long, User> users = new HashMap<>();

    private long getNextId() {
        return ++maxId;
    }

    @GetMapping
    public List<User> getAll() {
        return users.values().stream().toList();
    }

    @PostMapping
    public User create(@Valid @RequestBody User newUser) {
        newUser.setId(getNextId());

        fillAttrByDefault(newUser);

        users.put(newUser.getId(), newUser);

        return newUser;
    }

    @PutMapping
    public User update(@Valid @RequestBody User newUser) {
        if (newUser.getId() == null) {
            log.error("Не указан id пользователя");
            throw new FilmValidationException("Не указан id пользователя");
        }

        if (!users.containsKey(newUser.getId())) {
            log.error("Не найден пользователь");
            throw new FilmValidationException("Не найден пользователь");
        }

        fillAttrByDefault(newUser);
        users.put(newUser.getId(), newUser);

        return newUser;
    }

    private void fillAttrByDefault(final User user) {
        if (user.getName() == null)
            user.setName(user.getLogin());
    }
}
