package com.cameleoon.quotes.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Locale loc = LocaleContextHolder.getLocale();
    @Autowired
    @Qualifier("errorMessages")
    private MessageSource messageSource;

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleBind(Exception ex) {
        BindingResult bindingResult = (BindingResult) ex;
        List<ApiError> apiErrors = new ArrayList<>();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = fieldError.getField() + " " + fieldError.getDefaultMessage();
            ApiError apiError = new ApiError(userMessage, userMessage);
            apiErrors.add(apiError);
        }
        for (ObjectError objectError : bindingResult.getGlobalErrors()) {
            String userMessage = objectError.getDefaultMessage();
            ApiError apiError = new ApiError(userMessage, userMessage);
            apiErrors.add(apiError);
        }
        return new ResponseEntity<>(apiErrors, httpStatus);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex) {
        return createResponse(ex, messageSource.getMessage("user.notFound", null, loc), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<Object> handleQuoteNotFoundException(Exception ex) {
        return createResponse(ex, messageSource.getMessage("quote.notFound", null, loc), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipleVotingException.class)
    public ResponseEntity<Object> handleMultipleVotingException(Exception ex) {
        return createResponse(ex, messageSource.getMessage("quote.multipleVoting", null, loc), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownExceptions(Exception ex) {
        return createResponse(ex, messageSource.getMessage("unknown.exception", null, loc), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> createResponse(Exception ex, String errorText, HttpStatus httpStatus) {
        ApiError apiError = new ApiError(ex.getMessage(), errorText);
        List<ApiError> errors = new ArrayList<>();
        errors.add(apiError);
        return new ResponseEntity<>(errors, httpStatus);
    }
}
