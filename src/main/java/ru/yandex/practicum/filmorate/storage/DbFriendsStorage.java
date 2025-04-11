package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FriendsRepository;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.List;
import java.util.Optional;

@Component("DbFriendsStorage")
@RequiredArgsConstructor
public class DbFriendsStorage implements FriendsStorage {
    private final FriendsRepository   friendsRepository;
    @Override
    public void add(Long userId, Long friendId) {
        friendsRepository.add(userId, friendId);
    }

    @Override
    public boolean isPresent(Long userId, Long friendId) {
        Optional<Friendship> friendship =  friendsRepository.findByUserFriend(userId, friendId);

        return friendship.isPresent();
    }

    @Override
    public void update(Long userId, Long friendId, Boolean isAccepted) {
        Optional<Friendship> friendship = friendsRepository.findByUserFriend(userId, friendId);

        friendship.ifPresent(value -> friendsRepository.update(value, isAccepted));
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
