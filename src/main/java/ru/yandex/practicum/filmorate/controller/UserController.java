package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserStorage userStorage;

    @GetMapping
    public List<User> getAll() {
        return userStorage.getAll();
    }

    @PostMapping
    public User create(@Valid @RequestBody User newUser) {
        return userStorage.add(newUser);
    }

    @PutMapping
    public User update(@Valid @RequestBody User newUser) {
        return userStorage.update(newUser);
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody User user) {
        userStorage.delete(user);
    }
}
