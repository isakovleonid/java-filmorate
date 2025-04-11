package ru.yandex.practicum.filmorate.dto;

public interface DTOMapper<T, V> {
    public T toDTO(V v);

    public V fromDTO(T t);
}
