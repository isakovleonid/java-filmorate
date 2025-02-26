package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;

@RestControllerAdvice
class ControllerAdvice {
    @Getter
    class ErrorDescription {
        private String message;

        public ErrorDescription(String message) {
            this.message = message;
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FilmValidationException.class)
    public ErrorDescription handleException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }
}
