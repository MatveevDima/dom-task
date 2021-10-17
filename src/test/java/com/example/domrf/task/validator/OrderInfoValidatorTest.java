package com.example.domrf.task.validator;

import com.example.domrf.task.exception.ValidationException;
import com.example.domrf.task.model.OrderInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderInfoValidatorTest {

    @Test
    void validateName() {
        assertThrows(ValidationException.class, () -> OrderInfoValidator.validate(
                new OrderInfo(
                        2l,
                        null,
                        "89150241469",
                        "monk-pee@mail.ru",
                        "Volgsky d9, k6"
                )
        ));
    }

    @Test
    void validatePhone() {
        assertThrows(ValidationException.class, () -> OrderInfoValidator.validate(
                new OrderInfo(
                        2l,
                        "Vlad",
                        null,
                        "monk-pee@mail.ru",
                        "Volgsky d9, k6"
                )
        ));
    }

    @Test
    void validateMail() {
        assertThrows(ValidationException.class, () -> OrderInfoValidator.validate(
                new OrderInfo(
                        2l,
                        "Vlad",
                        "89150241469",
                        null,
                        "Volgsky d9, k6"
                )
        ));
    }

    @Test
    void validateAddress() {
        assertThrows(ValidationException.class, () -> OrderInfoValidator.validate(
                new OrderInfo(
                        2l,
                        "Vlad",
                        "89150241469",
                        "monk-pee@mail.ru",
                        null
                )
        ));
    }
}