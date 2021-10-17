package com.example.domrf.task.exception;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {

    private static final String TEXT_FORMAT_STATUS = "Requires param is missing: %s";

    public ValidationException(String field) {
        this(field, null);
    }

    public ValidationException(String field, Throwable cause) {
       super(String.format(TEXT_FORMAT_STATUS, field), cause);
    }
}