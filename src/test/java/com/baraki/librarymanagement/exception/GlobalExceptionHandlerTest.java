package com.baraki.librarymanagement.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenResourceNotFoundException_thenRespondWithNotFound() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found message");

        ResponseEntity<?> responseEntity = globalExceptionHandler.resourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void whenMethodArgumentNotValidException_thenRespondWithBadRequest() {
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new FieldError("objectName", "field", "default message"));
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "objectName");
        errors.forEach(bindingResult::addError);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void whenGenericException_thenRespondWithInternalServerError() {
        Exception exception = new Exception("Generic error message");

        ResponseEntity<?> responseEntity = globalExceptionHandler.globalExceptionHandler(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}