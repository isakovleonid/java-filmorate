package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class FilmLike {
    Long id;
    @NotNull
    Long filmId;
    @NotNull
    Long userId;
}
