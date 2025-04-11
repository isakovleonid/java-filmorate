package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.ValueOfEnum;

@Data
public class FilmGenre {
    Long id;
    @NotNull
    Long filmId;
    @NotNull
    Long genreId;
}
