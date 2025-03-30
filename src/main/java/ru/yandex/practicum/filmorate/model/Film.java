package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.DateRange;
import ru.yandex.practicum.filmorate.validation.ValueOfEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Film.
 */
@Data
public class Film {
    Long id;

    @NotBlank(message = "Наименование фильма должно быть заполнено")
    String name;

    @Size(max = 200, message = "Длина описание фильма не может превышать 200 символов")
    String description;

    @Past
    @DateRange(dateRangeBegin = "1895-12-28", message = "Дата выпуска фильма не может быть раньше 28/12/1895")
    LocalDate releaseDate;

    @Positive(message = "Длительность фильма должна быть больше 0")
    Long duration;

    Set<Genre> genreSet = new HashSet<>();
    MPARating mpaRating;
}
