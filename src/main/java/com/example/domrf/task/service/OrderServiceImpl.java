package com.example.domrf.task.service;

import com.example.domrf.task.dao.OrderRepository;
import com.example.domrf.task.exception.ApiException;
import com.example.domrf.task.model.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.domrf.task.resources.LoggerResources.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final static Logger LOG = Logger.getLogger(OrderServiceImpl.class.getCanonicalName());

    private final OrderRepository repository;

    @Override
    public void addOrder(OrderInfo orderInfo) {

        LOG.log(Level.INFO, ENTRY);

        try {
            repository.save(orderInfo);
        } catch (Exception e) {
           throw new ApiException(e.getMessage(), e);
        }

        LOG.log(Level.INFO, EXIT);
    }

    @Override
    public List<OrderInfo> getAllOrders() {

        LOG.log(Level.INFO, ENTRY);

        List<OrderInfo> response;
        try {
            response = repository.findAll();
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e);
        }

        LOG.log(Level.INFO, EXIT);

        return response;
    }

    @Override
    public List<OrderInfo> getOrderByPhone(String phone) {

        LOG.log(Level.INFO, ENTRY);

        List<OrderInfo> response;
        try {
            response = repository.findByPhone(phone);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e);
        }

        LOG.log(Level.INFO, EXIT);

        return response;
    }
}