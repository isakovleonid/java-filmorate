package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Film.
 */
@Data
public class Film {
    Long id;
    @NotNull
    @Pattern(regexp = "^(\\S|\\s)+$")
    String name;
    @Size(max = 200)
    String description;
    @Past
    LocalDate releaseDate;
    @Positive
    Long duration;
}
