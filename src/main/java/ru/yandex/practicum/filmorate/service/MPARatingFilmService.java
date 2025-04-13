package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.MPARatingFilm;
import ru.yandex.practicum.filmorate.storage.MPARatingFilmStorage;

import java.util.List;

@Service
public class MPARatingFilmService {
    private final MPARatingFilmStorage mpaRatingFilmStorage;

    @Autowired
    public MPARatingFilmService(@Qualifier("DbMPARatingFilmStorage") MPARatingFilmStorage mpaRatingFilmStorage) {
        this.mpaRatingFilmStorage = mpaRatingFilmStorage;
    }

    public MPARatingFilm getById(Long id) {
        return mpaRatingFilmStorage.getById(id);
    }

    public List<MPARatingFilm> getAll() {
        return mpaRatingFilmStorage.getAll();
    }
}
