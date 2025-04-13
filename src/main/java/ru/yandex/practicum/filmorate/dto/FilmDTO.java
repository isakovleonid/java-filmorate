package ru.yandex.practicum.filmorate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;
import ru.yandex.practicum.filmorate.validation.DateRange;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class FilmDTO {
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

    MPARatingFilm mpa;
    Set<GenreDict> genres = new HashSet<>();
}
