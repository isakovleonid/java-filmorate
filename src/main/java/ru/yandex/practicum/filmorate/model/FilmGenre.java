package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilmGenre {
    Long id;
    @NotNull
    Long filmId;
    @NotNull
    String genreId;
}
