create table if not exists MPARating (
    code        varchar(16) PRIMARY KEY,
    name        varchar(255)
);

merge into MPARating (code, name) key(code)
values  ('G', 'G'),
        ('PG', 'PG'),
        ('PG13', 'PG-13'),
        ('R', 'R'),
        ('NC17', 'NC-17');

create table if not exists Genre (
    code        varchar(16) PRIMARY KEY,
    name        varchar(255)
);

merge into Genre (code, name) key (code)
values  ('COMEDY', 'Комедия'),
        ('DRAMA', 'Драва'),
        ('CARTOON', 'Мультфильм'),
        ('THRILLER', 'Триллер'),
        ('DOCUMENTARY', 'Документарный'),
        ('ACTION', 'Приключение');

create table if not exists Film (
    id  long auto_increment primary key,
    name    varchar(255),
    description varchar(200),
    releaseDate date,
    duration    long,
    MPArating varchar(16),
    foreign key(MPARating) references MPARating(code)
);

create table if not exists Users (
    id          long auto_increment PRIMARY KEY,
    email       varchar(255),
    login       varchar(255),
    name        varchar(255),
    birthday    date
);

create table if not exists Friendship (
    id          long auto_increment PRIMARY KEY,
    userId      long,
    friendId    long,
    isAccepted  boolean,
    Foreign key(userId) references Users(id),
    Foreign key(friendId) references Users(id)
);

create table if not exists FilmRating (
    id  long auto_increment primary key,
    filmId  long,
    userId  long,
    Foreign key(filmId) references Film(id),
    foreign key(userId) references Users(id)
);

create table if not exists FilmGenre (
    id  long auto_increment primary key,
    filmId  long,
    genreId  varchar(16),
    Foreign key(filmId) references Film(id),
    foreign key(genreId) references Genre(code)
);