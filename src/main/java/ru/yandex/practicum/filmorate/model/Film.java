package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.DateRange;

import java.time.LocalDate;

/**
 * Film.
 */
@Data
public class Film {
    Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    String name;

    @Size(max = 200)
    String description;

    @Past
    @DateRange(dateRangeBegin = "1895-12-28")
    LocalDate releaseDate;

    @Positive
    Long duration;
}
