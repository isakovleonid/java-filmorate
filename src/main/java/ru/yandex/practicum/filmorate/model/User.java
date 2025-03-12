package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Long id;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    private String login;

    private String name;

    @Past
    private LocalDate birthday;
}
