merge into Genre (code, name) key (code)
values  ('COMEDY', 'Комедия'),
        ('DRAMA', 'Драма'),
        ('CARTOON', 'Мультфильм'),
        ('THRILLER', 'Триллер'),
        ('DOCUMENTARY', 'Документальный'),
        ('ACTION', 'Боевик');

merge into MPARating (code, name) key(code)
values  ('G', 'G'),
        ('PG', 'PG'),
        ('PG13', 'PG-13'),
        ('R', 'R'),
        ('NC17', 'NC-17');