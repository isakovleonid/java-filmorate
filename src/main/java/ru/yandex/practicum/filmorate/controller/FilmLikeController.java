package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmLikeService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmLikeController {
    private final FilmLikeService filmLikeService;

    @PutMapping("/{filmId}/like/{userId}")
    public void addLike(@PathVariable("filmId") @NotNull Long filmId, @PathVariable("userId") @NotNull Long userId) {
        filmLikeService.add(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public void deleteLike(@PathVariable("filmId") @NotNull Long filmId, @PathVariable("userId") @NotNull Long userId) {
        filmLikeService.delete(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(name = "count", defaultValue = "10") Long count) {
        return filmLikeService.getMostPopularFilms(count);
    }
}
