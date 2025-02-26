package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    Long id;
    @Email
    String email;
    @NotNull
    @Pattern(regexp = "^\\S+$")
    String login;
    String name;
    @Past
    LocalDate birthday;
}
