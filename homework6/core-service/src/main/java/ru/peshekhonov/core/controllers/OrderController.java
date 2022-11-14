package ru.peshekhonov.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.api.dto.OrderDetailsDto;
import ru.peshekhonov.api.dto.OrderDto;
import ru.peshekhonov.core.services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody(required = false) Optional<OrderDetailsDto> orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        return orderService.findAllOrderDtoByUsername(username);
    }
}
