package ru.yandex.practicum.filmorate.model;

public enum Genre {
    COMEDY,
    DRAMA,
    CARTOON,
    THRILLER,
    DOCUMENTARY,
    ACTION;

    public static Genre from(String genre) {
        if (genre == null) {
            return null;
        }

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

    public static Genre from(Long id) {
        if (id == null) {
            return null;
        }

        return switch (id.intValue()) {
            case 1 -> COMEDY;
            case 2 -> DRAMA;
            case 3 -> CARTOON;
            case 4 -> THRILLER;
            case 5 -> DOCUMENTARY;
            case 6 -> ACTION;
            default -> null;
        };
    }
}
