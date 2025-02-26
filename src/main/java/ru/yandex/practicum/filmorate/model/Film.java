package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.DateRange;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @DateRange(dateRangeBegin = "1895-12-28")
    LocalDate releaseDate;
    @Positive
    Long duration;
}
