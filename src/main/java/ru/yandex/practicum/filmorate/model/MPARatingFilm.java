package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MPARatingFilm {
    @NotNull
    Long id;

    @NotNull
    String name;
}
