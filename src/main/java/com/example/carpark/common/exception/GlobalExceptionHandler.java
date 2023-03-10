package com.example.carpark.common.exception;



import com.example.carpark.common.dto.Response;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CarParkException.EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(CarParkException.EntityNotFoundException e) {
        Response response = Response.notFound();
        response.setMessage(e.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarParkException.EntityAlreadyExistsException.class)
    public ResponseEntity handleEntityAlreadyExistsException(CarParkException.EntityAlreadyExistsException e) {
        Response response = Response.badRequest();
        response.setMessage(e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    // parse JSON error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Response response = Response.badRequest();
        response.setMessage(e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Response response = Response.badRequest();

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        response.setError(errors);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        Response response = Response.badRequest();
        response.setMessage(e.getConstraintViolations().stream().map(x -> {
            String message = x.getMessage();
            try {
            Pattern pattern = Pattern.compile("com\\.example\\.carpark\\.([a-z\\.])*[A-Z]\\w*");
            Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    String entityName = matcher.group(0);
                        Class<?> entityClass = Class.forName(entityName);
                        message = "Available field: " + Stream.of(entityClass.getDeclaredFields())
                                .map(Field::getName)
                                .collect(Collectors.joining(", "));
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            return message;
        }).collect(Collectors.joining(", ")));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarParkException.CommonLogicExeption.class)
    public ResponseEntity handleCommonLogicException(CarParkException.CommonLogicExeption e) {
        Response response = Response.badRequest();
        response.setMessage(e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        Response response = Response.internalServerError();
        response.setMessage(e.getMessage());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
