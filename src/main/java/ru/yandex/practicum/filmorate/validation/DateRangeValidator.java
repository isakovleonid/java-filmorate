package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {
    LocalDate dateRangeBegin;
    LocalDate dateRangeEnd;

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return (dateRangeBegin == null || !localDate.isBefore(dateRangeBegin))
               && (dateRangeEnd == null || !localDate.isAfter(dateRangeEnd));
    }

    @Override
    public void initialize(DateRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        if (constraintAnnotation.dateRangeBegin().isEmpty())
            dateRangeBegin = null;
        else
            dateRangeBegin = LocalDate.parse(constraintAnnotation.dateRangeBegin(), DateTimeFormatter.ISO_DATE);

        if (constraintAnnotation.dateRangeEnd().isEmpty())
            dateRangeEnd = null;
        else
            dateRangeEnd = LocalDate.parse(constraintAnnotation.dateRangeEnd(), DateTimeFormatter.ISO_DATE);
    }
}
