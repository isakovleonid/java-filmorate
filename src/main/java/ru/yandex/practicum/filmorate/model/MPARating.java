package ru.yandex.practicum.filmorate.model;

public enum MPARating {
    G,
    PG,
    PG13,
    R,
    NC17;

    public static MPARating from(String mpaRating) {
        if (mpaRating == null) {
            return null;
        } else {
            return switch (mpaRating.toUpperCase()) {
                case "G" -> G;
                case "PG" -> PG;
                case "PG13", "PG-13" -> PG13;
                case "R" -> R;
                case "NC17", "NC-17" -> NC17;
                default -> null;
            };
        }
    }

    public static MPARating from(Long id) {
        if (id == null) {
            return null;
        } else {
            return switch (id.intValue()) {
                case 1 -> G;
                case 2 -> PG;
                case 3 -> PG13;
                case 4 -> R;
                case 5 -> NC17;
                default -> null;
            };
        }
    }
}
