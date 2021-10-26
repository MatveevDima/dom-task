package com.example.domrf.task.controller;

import com.example.domrf.task.dto.OrderResponseDto;
import com.example.domrf.task.dto.OrderResultDto;
import com.example.domrf.task.model.OrderInfo;
import com.example.domrf.task.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.domrf.task.resources.LoggerResources.ENTRY;
import static com.example.domrf.task.resources.LoggerResources.EXIT;
import static com.example.domrf.task.resources.SwaggerResources.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = ORDER_SERVICE, description = ORDER_SERVICE_DESC)
public class OrderController {

    private final static Logger LOG = Logger.getLogger(OrderController.class.getCanonicalName());
    private final OrderService orderService;

    @Operation(summary = ADD_ORDER_POST,
            description = ADD_ORDER_POST_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponseDto.class))),
                    @ApiResponse(description = "Validation error",
                            responseCode = "400",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponseDto.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponseDto.class)))})
    @PostMapping(value = "/add-order", produces = APPLICATION_JSON_VALUE)
    private ResponseEntity<OrderResponseDto> addOrder(@Valid @RequestBody OrderInfo orderInfo) {

        LOG.log(Level.INFO, ENTRY);

        orderService.addOrder(orderInfo);

        LOG.log(Level.INFO, EXIT);

        return ResponseEntity.status(HttpStatus.OK).body(
                new OrderResponseDto(true, LocalDateTime.now(), "Order created."));
    }

    @Operation(summary = GET_ALL_ORDERS,
            description = GET_ALL_ORDERS_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResultDto.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponseDto.class)))})
    @GetMapping(value = "/get-all-orders", produces = APPLICATION_JSON_VALUE)
    private ResponseEntity<OrderResultDto> getAllOrders() {

        LOG.log(Level.INFO, ENTRY);

        List<OrderInfo> result = orderService.getAllOrders();

        LOG.log(Level.INFO, EXIT);

        return ResponseEntity.status(HttpStatus.OK).body(new OrderResultDto(result));
    }

    @Operation(summary = GET_ORDER_BY_PHONE,
            description = GET_ORDER_BY_PHONE_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResultDto.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponseDto.class)))})
    @GetMapping(value = "/get-order-by-phone/{phone}", produces = APPLICATION_JSON_VALUE)
    private ResponseEntity<OrderResultDto> getOrderByPhone(@PathVariable(value = "phone") String phone) {

        LOG.log(Level.INFO, ENTRY);

        List<OrderInfo> result = orderService.getOrderByPhone(phone);

        LOG.log(Level.INFO, EXIT);

        return ResponseEntity.status(HttpStatus.OK).body(new OrderResultDto(result));
    }
}