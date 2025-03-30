package ru.yandex.practicum.filmorate.model;

public enum Genre {
    COMEDY,
    DRAMA,
    CARTOON,
    THRILLER,
    DOCUMENTARY,
    ACTION;

    public static Genre from(String genre) {
        return switch (genre.toLowerCase()) {
            case "комедия", "comedy" -> COMEDY;
            case "драма", "drama" -> DRAMA;
            case "мультфильм", "cartoon" -> CARTOON;
            case "триллер", "thriller" -> THRILLER;
            case "документарный", "documentary" -> DOCUMENTARY;
            case "боевик", "action" -> ACTION;
            default -> null;
        };
    }
}
