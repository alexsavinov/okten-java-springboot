package okten.javaspringboot.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import okten.javaspringboot.models.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO errorValidation(ConstraintViolationException ex) {
        ConstraintViolation violation = ex.getConstraintViolations().stream().findFirst().get();
        String message = violation.getPropertyPath() + " - " + violation.getMessage();
        return new ErrorDTO(500, message);
    }
}
