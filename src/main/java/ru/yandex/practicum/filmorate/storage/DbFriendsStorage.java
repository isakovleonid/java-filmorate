package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FriendsRepository;

import java.util.List;

@Component("DbFriendsStorage")
@RequiredArgsConstructor
public class DbFriendsStorage implements FriendsStorage {
    private final FriendsRepository   friendsRepository;

    @Override
    public void add(Long userId, Long friendId) {
        friendsRepository.add(userId, friendId);
    }

    @Override
    public void delete(Long userId, Long friendId) {
        friendsRepository.delete(userId, friendId);
    }

    @Override
    public void deleteUser(Long userId) {
        friendsRepository.deleteByUserId(userId);
    }

    @Override
    public List<Long> getAllUserFriends(Long userId) {
        return friendsRepository.findAllByUser(userId);
    }

    @Override
    public List<Long> getCommonFriends(Long userId, Long otherUserId) {
        return friendsRepository.findCommonFriends(userId, otherUserId);
    }
}
