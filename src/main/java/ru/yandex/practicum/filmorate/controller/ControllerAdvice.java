package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;

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
    @ExceptionHandler()
    public ErrorDescription handleBindException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({FilmorateValidationException.class, MethodArgumentNotValidException.class})
    public ErrorDescription handleFilmorateValidationException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({FilmorateNotFoundException.class})
    public ErrorDescription handleFilmorateNotFoundException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }
}
