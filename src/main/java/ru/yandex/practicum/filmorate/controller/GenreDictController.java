package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.GenreDict;
import ru.yandex.practicum.filmorate.service.GenreDictService;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreDictController {
    private final GenreDictService genreFilmService;

    @GetMapping("/{id}")
    public GenreDict getById(@PathVariable("id") Long id) {
        return genreFilmService.getById(id);
    }

    @GetMapping
    public List<GenreDict> getAll() {
        return genreFilmService.getAll();
    }
}
