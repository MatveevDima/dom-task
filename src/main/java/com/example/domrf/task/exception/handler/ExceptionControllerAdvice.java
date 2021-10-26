package com.example.domrf.task.exception.handler;

import com.example.domrf.task.dto.OrderResponseDto;
import com.example.domrf.task.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.domrf.task.resources.LoggerResources.THROW;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final static Logger LOG = Logger.getLogger(ExceptionControllerAdvice.class.getCanonicalName());
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<OrderResponseDto> handleApiException(ApiException aex,
                                                               WebRequest request) {
        return handleExceptionByStatus(aex, request, INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(BAD_REQUEST)
    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<OrderResponseDto> handleValidationException(MethodArgumentNotValidException vex,
                                                                      WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new OrderResponseDto(false, LocalDateTime.now(),
                        String.format("The parameter is incorrect: %s", vex.getFieldError().getField())));
    }

    private ResponseEntity<OrderResponseDto> handleExceptionByStatus(Exception ex,
                                                                     WebRequest request,
                                                                     HttpStatus httpStatus) {
        LOG.log(Level.WARNING, THROW, ex);

        return ResponseEntity.status(httpStatus).body(new OrderResponseDto(false, LocalDateTime.now(), ex.getMessage()));
    }
}