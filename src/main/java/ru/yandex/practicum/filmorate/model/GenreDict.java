package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenreDict {
    @NotNull
    Long id;

    @NotNull
    String name;
}
