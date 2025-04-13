package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;
import ru.yandex.practicum.filmorate.service.MPARatingFilmService;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MPARatingFilmController {
    private final MPARatingFilmService mpaRatingFilmService;

    @GetMapping
    public List<MPARatingFilm> getAll() {
        return mpaRatingFilmService.getAll();
    }

    @GetMapping("/{id}")
    public MPARatingFilm getById(@PathVariable("id") Long id) {
        return mpaRatingFilmService.getById(id);
    }
}