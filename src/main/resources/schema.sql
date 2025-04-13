create table if not exists MPARating (
    id          long auto_increment primary key,
    code        varchar(16),
    name        varchar(255)
);



create table if not exists Genre (
    id          long auto_increment PRIMARY KEY,
    code        varchar(16),
    name        varchar(255)
);

create table if not exists Film (
    id  long auto_increment primary key,
    name    varchar(255),
    description varchar(200),
    releaseDate date,
    duration    long,
    MPArating long,
    foreign key(MPARating) references MPARating(id)
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
    Foreign key(userId) references Users(id),
    Foreign key(friendId) references Users(id),
    unique(userId, friendId)
);

create table if not exists FilmLike (
    id  long auto_increment primary key,
    filmId  long,
    userId  long,
    Foreign key(filmId) references Film(id),
    foreign key(userId) references Users(id),
    unique(filmId, userId)
);

create table if not exists FilmGenre (
    id  long auto_increment primary key,
    filmId  long,
    genreId  varchar(16),
    Foreign key(filmId) references Film(id),
    foreign key(genreId) references Genre(id),
    UNIQUE(filmId, genreId)
);