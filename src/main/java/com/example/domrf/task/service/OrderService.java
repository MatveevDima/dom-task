package com.example.domrf.task.service;

import com.example.domrf.task.model.OrderInfo;

import java.util.List;

public interface OrderService {

    void addOrder(OrderInfo orderInfo);

    List<OrderInfo> getAllOrders();

    List<OrderInfo> getOrderByPhone(String phone);
}