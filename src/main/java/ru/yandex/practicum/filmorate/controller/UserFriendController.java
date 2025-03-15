package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserFriendService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/friends")
@Slf4j
@RequiredArgsConstructor
public class UserFriendController {
    private final UserFriendService userFriendService;

    @PutMapping("/{friendId}")
    public void addFriend(@PathVariable("userId") @NotNull Long userId, @PathVariable("friendId") @NotNull Long friendId) {
        userFriendService.addFriend(userId, friendId);
    }

    @GetMapping
    public List<User> getAlltUserFriends(@PathVariable("userId") @NotNull Long userId) {
        return userFriendService.getAllUserFriends(userId);
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriend(@PathVariable("userId") @NotNull Long userId, @PathVariable("friendId") @NotNull Long friendId) {
        userFriendService.deleteFriend(userId, friendId);
    }

    @GetMapping("/common/{otherId}")
    public List<User> commonFriends(@PathVariable("userId") @NotNull Long userId, @PathVariable("otherId") @NotNull Long otherUserId) {
        return userFriendService.getCommonFriends(userId, otherUserId);
    }
}
