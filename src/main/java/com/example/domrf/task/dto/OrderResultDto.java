package com.example.domrf.task.dto;

import com.example.domrf.task.model.OrderInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResultDto extends OrderResponseDto{

    public OrderResultDto(List<OrderInfo> result) {
        super.setSuccess(true);
        super.setTimestamp(LocalDateTime.now());
        super.setMessage(result.isEmpty() ? "Orders is not found." : result.size() + " order(s) found.");
        this.result = result;
    }

    private List<OrderInfo> result;
}
