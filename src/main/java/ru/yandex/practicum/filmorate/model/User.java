package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;

    @Email(message = "Адрес электронной почтый должен соответствовать формату email")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать whitespace-символы")
    private String login;

    private String name;

    @Past(message="Дата рождения должна быть в прошлом")
    private LocalDate birthday;
}
