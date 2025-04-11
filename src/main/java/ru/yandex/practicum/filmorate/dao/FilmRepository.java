package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository extends BaseRepository {
    public FilmRepository(JdbcTemplate jdbcTemplate, RowMapper<Film> mapper) {
        super(jdbcTemplate, mapper);
    }
    private final String INSERT_QUERY = "insert into Film(name, description, releaseDate, duration, MPArating) " +
            "values (?, ?, ?, ?, ?)";

    private final String UPDATE_QUERY = "update Film " +
            " set name = ?, description = ?, releaseDate = ?, duration = ?, MPArating = ?  " +
            " where id = ?";

    private final String DELETE_QUERY = "delete Film " +
            " where id = ?";

    private final String FIND_ALL = "select * from Film";

    private final String FIND_ONE_BY_ID = "select * from Film " +
            "where id = ?";

    private final String FIND_MOST_POPULAR = "select *\n" +
            "from\n" +
            "    film f\n" +
            "where\n" +
            "    f.id in (select fr.filmId\n" +
            "             from \n" +
            "                    FilmRating fr\n" +
            "             group by \n" +
            "                    fr.filmId\n" +
            "             order by\n" +
            "                    count(fr.filmId)\n" +
            "             limit ?)";

    public Film add(Film film) {
        long id = insert(INSERT_QUERY, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(), film.getMpa());

        film.setId(id);

        return film;
    }

    public Film update(Film film) {
        update(UPDATE_QUERY,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate().format(DateTimeFormatter.ISO_DATE),
                film.getDuration(),
                film.getMpa(),
                film.getId()
        );

        return film;
    }

    public void delete(Long id) {
        delete(DELETE_QUERY, id);
    }

    public List<Film> findAll() {
        return findList(FIND_ALL);
    }

    public Optional<Film> findById(Long id) {
        return findOne(FIND_ONE_BY_ID, id);
    }

    public List<Film> findMostPopularFilms(Long filmCount) {
        return findList(FIND_MOST_POPULAR, filmCount);
    }
}
