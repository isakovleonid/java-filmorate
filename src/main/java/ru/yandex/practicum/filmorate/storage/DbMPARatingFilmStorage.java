package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.MPARatingFilmRepository;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;

import java.util.List;

@Component("DbMPARatingFilmStorage")
@RequiredArgsConstructor
public class DbMPARatingFilmStorage implements MPARatingFilmStorage {
    private final MPARatingFilmRepository mpaRatingFilmRepository;

    @Override
    public List<MPARatingFilm> getAll() {
        return mpaRatingFilmRepository.getAll();
    }

    @Override
    public MPARatingFilm getById(Long id) {
        return mpaRatingFilmRepository.getById(id);
    }
}
