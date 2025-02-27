package ru.yandex.practicum.filmorate.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;

import java.time.LocalDate;

public class LocalFormatter {
    @Bean
    @Primary
    public Formatter<LocalDate> localDateFormatter() {
        return new LocalDateFormatter();
    }
}
