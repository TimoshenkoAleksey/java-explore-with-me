package ru.practicum.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.error.exeption.BadRequestException;
import ru.practicum.error.exeption.ConflictException;
import ru.practicum.error.exeption.NotFoundException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MissingPathVariableException.class,
            BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(Exception e) throws Exception {
        if (e instanceof ConstraintViolationException ||
                e instanceof MethodArgumentNotValidException ||
                e instanceof MissingPathVariableException ||
                e instanceof BadRequestException) {
            return new ErrorResponse("Validation error: ", e.getMessage());
        }
        throw e;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        return new ErrorResponse("Not found error: ", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        return new ErrorResponse("Category name already exists.", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(final ConflictException e) {
        return new ErrorResponse("Conflict error: ", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ru.practicum.exception.ErrorResponse handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        return new ru.practicum.exception.ErrorResponse("Validation error: ", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(final RuntimeException e) {
        return new ErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ru.practicum.exception.ErrorResponse handleThrowable(final Throwable e) {
        return new ru.practicum.exception.ErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
    }
}
