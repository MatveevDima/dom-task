package com.example.domrf.task.validator;

import com.example.domrf.task.exception.ValidationException;
import com.example.domrf.task.model.OrderInfo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class OrderInfoValidator {

    public static void validate(OrderInfo orderInfo) {

        validateName(orderInfo);
        validatePhone(orderInfo);
        validateMail(orderInfo);
        validateAddress(orderInfo);
    }

    @SneakyThrows
    private static void validateName(OrderInfo orderInfo) {
        if (StringUtils.isEmpty(orderInfo.getName())) {
            throw new ValidationException("name");
        }
    }

    @SneakyThrows
    private static void validatePhone(OrderInfo orderInfo) {
        if (StringUtils.isEmpty(orderInfo.getPhone())) {
            throw new ValidationException("phone");
        }
    }

    @SneakyThrows
    private static void validateMail(OrderInfo orderInfo) {
        if (StringUtils.isEmpty(orderInfo.getMail())) {
            throw new ValidationException("mail");
        }
    }

    @SneakyThrows
    private static void validateAddress(OrderInfo orderInfo) {
        if (StringUtils.isEmpty(orderInfo.getAddress())) {
            throw new ValidationException("address");
        }
    }
}
