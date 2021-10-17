package com.example.domrf.task.exception.handler;

import com.example.domrf.task.dto.OrderResponseDto;
import com.example.domrf.task.exception.ApiException;
import com.example.domrf.task.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.domrf.task.resources.LoggerResources.THROW;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final static Logger LOG = Logger.getLogger(ExceptionControllerAdvice.class.getCanonicalName());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(produces = "application/json;charset=utf-8")
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<OrderResponseDto> handleValidationException(ValidationException vex,
                                                                      WebRequest request) {
        return handleExceptionByStatus(vex, request, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(produces = "application/json;charset=utf-8")
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<OrderResponseDto> handleApiException(ApiException aex,
                                                               WebRequest request) {
        return handleExceptionByStatus(aex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<OrderResponseDto> handleExceptionByStatus(Exception ex,
                                                                     WebRequest request,
                                                                     HttpStatus httpStatus) {
        LOG.log(Level.WARNING, THROW, ex);

        return ResponseEntity.status(httpStatus).body(new OrderResponseDto(false, LocalDateTime.now(), ex.getMessage()));
    }
}