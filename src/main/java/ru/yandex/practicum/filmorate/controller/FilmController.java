package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.dto.FilmDTOMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final FilmDTOMapper filmDTOMapper;

    @GetMapping
    public List<FilmDTO> getAll() {
        return filmService.getAll().stream()
                .map(filmDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FilmDTO getById(@PathVariable("id") Long id) {
        return filmDTOMapper.toDTO(filmService.getFilm(id));
    }

    @PostMapping
    public FilmDTO add(@Valid @RequestBody FilmDTO newFilm) {
        Film film = filmDTOMapper.fromDTO(newFilm);
        return filmDTOMapper.toDTO(filmService.add(film));
    }

    @PutMapping
    public FilmDTO update(@Valid @RequestBody FilmDTO newFilm) {
        Film film = filmDTOMapper.fromDTO(newFilm);
        return filmDTOMapper.toDTO(filmService.update(film));
    }

    @DeleteMapping
    public void delete(@PathVariable("id") @NotNull Long id) {
        filmService.delete(id);
    }
}
