package com.example.domrf.task.service;

import com.example.domrf.task.dao.OrderRepository;
import com.example.domrf.task.exception.ApiException;
import com.example.domrf.task.model.OrderInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    @Autowired
    private OrderServiceImpl orderService;

    private OrderInfo dima = new OrderInfo(
            1l,
            "Dima",
            "89150241468",
            "monk-dee@mail.ru",
            "Volgsky d8, k5");

    private OrderInfo vlad = new OrderInfo(
            2l,
            "Vlad",
            "89150241469",
            "monk-pee@mail.ru",
            "Volgsky d9, k6"
    );

    @Test
    void getAllOrders() {

        when(repository.findAll())
                .thenReturn(new ArrayList<>(Arrays.asList(dima, vlad)));

        assertEquals(2, orderService.getAllOrders().size());
        assertTrue(orderService.getAllOrders().contains(dima));
        assertTrue(orderService.getAllOrders().contains(vlad));
    }

    @Test
    void getAllOrdersEmptyResult() {

        when(repository.findAll())
                .thenReturn(new ArrayList<>());

        assertEquals(0, orderService.getAllOrders().size());
        assertFalse(orderService.getAllOrders().contains(dima));
    }

    @Test
    void getOrderByPhone() {

        when(repository.findByPhone("89150241468")).thenReturn(
                new ArrayList<>(Collections.singletonList(dima)));

        when(repository.findByPhone("89150241469")).thenReturn(new ArrayList<>());

        assertEquals(1, orderService.getOrderByPhone("89150241468").size());
        assertTrue(orderService.getOrderByPhone("89150241468").contains(dima));
        assertEquals(0, orderService.getOrderByPhone("89150241469").size());
    }

    @Test
    void addOrderException() {

        when(repository.save(dima)).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> orderService.addOrder(dima));
    }
}
