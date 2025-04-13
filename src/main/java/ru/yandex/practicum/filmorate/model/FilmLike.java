package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilmLike {
    Long id;
    @NotNull
    Long filmId;
    @NotNull
    Long userId;
}
