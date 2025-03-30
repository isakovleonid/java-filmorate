package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Friendship {
    @NotNull
    Long    friendId;

    @NotNull
    boolean isAccepted;
}
