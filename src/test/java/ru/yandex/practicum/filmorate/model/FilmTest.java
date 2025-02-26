package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {
    Film film;
    Validator validator;
    Set<ConstraintViolation<Film>> violations;

    @BeforeEach
    void beforeEach() {
        film = new Film();
        film.setName("Двенадцать");
        film.setDescription("Драма");
        film.setDuration(90L);
        film.setReleaseDate(LocalDate.parse("2007-12-25", DateTimeFormatter.ISO_DATE));

        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void setName() {
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное наименование");

        film.setName("");
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное наименование - пустое");

        film.setName(null);
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное наименование - null");

        film.setName(" ");
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное наименование - whitespace");
    }

    @Test
    void setDescription() {
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное описание");

        film.setDescription("");
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное описание - пустое");

        film.setDescription(null);
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное описание - null");

        film.setDescription(" ");
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное описание - whitespace");

        film.setDescription("Пятеро друзей ( комик-группа «Шарло»), приезжают в город Бризуль. Здесь они хотят разыскать господина Огюста Куглова" +
                ", который задолжал им деньги, а именно 20 миллионов. о Куглов, который за время «своего отсутствия», стал кандидатом Коломбани.");
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное наименование - длина больше 200 символов");
    }

    @Test
    void setDuration() {
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректную длительность");

        film.setDuration(null);
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректную длительность - null");

        film.setDuration(-5L);
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное описание - отрицательное значение");

        film.setDuration(0L);
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное описание - нулевое значение");
    }

    @Test
    void setReleaseDate() {
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректную дата выпуска");

        film.setReleaseDate(LocalDate.parse("1895-12-28", DateTimeFormatter.ISO_DATE));
        violations = validator.validate(film);
        assertEquals(0, violations.size(), "Не удалось присвоить корректное описание - минимальное значение");

        film.setReleaseDate(LocalDate.now());
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректное описание - текущую дату");

        film.setReleaseDate(LocalDate.parse("1890-01-01", DateTimeFormatter.ISO_DATE));
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректную дату выпуска - меньше нижней границы");

        film.setReleaseDate(LocalDate.now().plusDays(10));
        violations = validator.validate(film);
        assertNotEquals(0, violations.size(), "Удалось присвоить некорректную дату выпуска - будущая дата");
    }
}