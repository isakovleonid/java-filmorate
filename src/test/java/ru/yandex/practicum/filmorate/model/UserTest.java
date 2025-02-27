package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class UserTest {
    User user;
    Validator validator;
    Set<ConstraintViolation<User>> violations;

    @BeforeEach
    void beforeEach() {
        user = new User();

        user.setLogin("vanya");
        user.setBirthday(LocalDate.parse("1990-12-25", DateTimeFormatter.ISO_DATE));
        user.setEmail("vanya@mail.ru");

        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void setEmail() {
        violations = validator.validate(user);
        assertEquals(0, violations.size(), "Не удалось присвоить корректный email");

        user.setEmail("Просто");
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректный email - не является email");

    }

    @Test
    void setLogin() {
        violations = validator.validate(user);
        assertEquals(0, violations.size(), "Не удалось присвоить корректный логин");

        user.setLogin("");
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректный логин - пустой");

        user.setLogin(null);
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректный логин - null");

        user.setLogin(" ");
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректный логин - whitespace");

        user.setLogin("vanya ivanov");
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректный логин - содержит пробел");
    }

    @Test
    void setBirthday() {
        violations = validator.validate(user);
        assertEquals(0, violations.size(), "Не удалось присвоить корректную дату рождения");

        user.setBirthday(LocalDate.now());
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное дату рождения - текущую дату");

        user.setBirthday(LocalDate.now().plusDays(10));
        violations = validator.validate(user);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректную дату рождения - будущая дата");
    }
}