package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLDublicateException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@Service("DbUserStorage")
@RequiredArgsConstructor
@Slf4j
public class DbUserStorage implements UserStorage {
    private final UserRepository userRepository;

    @Override
    public User add(User newUser) {
        fillAttrByDefault(newUser);

        if (userRepository.findByLogin(newUser.getLogin()).isPresent()) {
            throw new FilmorateSQLDublicateException("Уже заведен пользователь с логином" + newUser.getLogin());
        }

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new FilmorateSQLDublicateException("Уже заведен пользователь с email " + newUser.getEmail());
        }

        newUser = userRepository.add(newUser);
        return newUser;
    }

    @Override
    public boolean checkExists(Long id) {
        if (id == null) {
            log.error("Не указан id пользователя");
            throw new FilmorateValidationException("Не указан id пользователя");
        }

        if (userRepository.findById(id).isEmpty()) {
            log.error("Не найден пользователь c id = {}", id);
            throw new FilmorateNotFoundException("Не найден пользователь c id = " + id);
        }

        return true;
    }

    @Override
    public void delete(Long userId) {
        checkExists(userId);

        userRepository.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User update(User user) {
        checkExists(user.getId());

        return userRepository.update(user);
    }
}
