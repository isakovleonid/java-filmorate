package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendshipService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/friends")
@Slf4j
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @PutMapping("/{friendId}")
    public void add(@PathVariable("userId") @NotNull Long userId, @PathVariable("friendId") @NotNull Long friendId) {
        friendshipService.add(userId, friendId);
    }

    @GetMapping
    public List<User> getAlltUserFriends(@PathVariable("userId") @NotNull Long userId) {
        return friendshipService.getAllUserFriends(userId);
    }

    @DeleteMapping("/{friendId}")
    public void delete(@PathVariable("userId") @NotNull Long userId, @PathVariable("friendId") @NotNull Long friendId) {
        friendshipService.delete(userId, friendId);
    }

    @GetMapping("/common/{otherId}")
    public List<User> commonFriends(@PathVariable("userId") @NotNull Long userId, @PathVariable("otherId") @NotNull Long otherUserId) {
        return friendshipService.getCommonFriends(userId, otherUserId);
    }
}
