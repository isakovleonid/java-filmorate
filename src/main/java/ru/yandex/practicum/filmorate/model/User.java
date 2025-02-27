package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    Long id;

    @Email
    String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    String login;

    String name;

    @Past
    LocalDate birthday;
}
