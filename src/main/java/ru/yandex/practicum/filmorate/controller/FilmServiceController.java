package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmLikesStorage;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmServiceController {
    private final FilmService filmService;

    @PutMapping("/{filmId}/like/{userId}")
    public void addLike(@PathVariable("filmId") @NotNull Long filmId, @PathVariable("userId") @NotNull Long userId ) {
        filmService.add(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public void deleteLike(@PathVariable("filmId") @NotNull Long filmId, @PathVariable("userId") @NotNull Long userId ) {
        filmService.delete(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(name="count", defaultValue = "10") Long count) {
        return filmService.getMostPopularFilms(count);
    }
}
