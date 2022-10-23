package ru.peshekhonov.online.store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.online.store.dto.OrderDetailsDto;
import ru.peshekhonov.online.store.dto.OrderDto;
import ru.peshekhonov.online.store.entities.Visitor;
import ru.peshekhonov.online.store.exceptions.ResourceNotFoundException;
import ru.peshekhonov.online.store.services.OrderService;
import ru.peshekhonov.online.store.services.VisitorService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final VisitorService visitorService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody(required = false) Optional<OrderDetailsDto> orderDetailsDto) {
        String username = principal.getName();
        Visitor visitor = visitorService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь '" + username + "' не найден"));

        orderService.createOrder(visitor, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrderDtoByUsername(principal.getName());
    }
}
