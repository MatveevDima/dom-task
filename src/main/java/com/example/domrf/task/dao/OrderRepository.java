package com.example.domrf.task.dao;

import com.example.domrf.task.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findByPhone(String phone);
}