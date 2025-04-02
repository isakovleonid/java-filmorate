package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.FilmorateNotFoundException;
import ru.yandex.practicum.filmorate.exception.FilmorateSQLException;
import ru.yandex.practicum.filmorate.exception.FilmorateValidationException;

import java.util.stream.Collectors;

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
    @ExceptionHandler({FilmorateValidationException.class, FilmorateSQLException.class})
    public ErrorDescription handleFilmorateValidationException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDescription handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorDescription(e.getBindingResult().getFieldErrors().stream()
                .map(a -> a.getDefaultMessage())
                .collect(Collectors.toSet())
                .toString());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({FilmorateNotFoundException.class})
    public ErrorDescription handleFilmorateNotFoundException(Exception e) {
        return new ErrorDescription(e.getMessage());
    }
}
